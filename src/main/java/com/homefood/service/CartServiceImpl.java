package com.homefood.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homefood.codetype.CartStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Cart;
import com.homefood.model.CartResponse;
import com.homefood.model.CartTotal;
import com.homefood.model.Product;
import com.homefood.model.User;
import com.homefood.repository.CartRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart createCart(Cart cart) {
		Cart existingCart = cartRepository.findByProductAndCustomerAndStatus(cart.getProduct(), cart.getCustomer(),
				CartStatus.ACTIVE);

		if (null != existingCart) {
			existingCart.setQuantity(existingCart.getQuantity() + 1);
			return cartRepository.save(existingCart);
		} 
		return cartRepository.save(cart);
	}

	@Override
	public Cart readById(long id) {
		return cartRepository.readByCartid(id);
	}

	@Override
	public List<Cart> readAllActiveByCustomer(User customer) {
		return cartRepository.findByCustomerAndStatus(customer, CartStatus.ACTIVE);
	}

	@Override
	public void processAllByCustomer(User customer) {
		cartRepository.updateStatusByCustomer(CartStatus.PROCESSED, customer);
	}

	@Override
	public Cart removeFromCart(Cart cart) {
		cart = readById(cart.getCartid());
		cart.setStatus(CartStatus.REMOVED);
		return createCart(cart);
	}

	@Override
	public CartTotal computeCart(User customer) {
		List<Cart> activeCartProducts = cartRepository.findByCustomerAndStatus(customer, CartStatus.ACTIVE);
		double totalCartPrice = 0;
		CartResponse cartResponse;
		ListIterator<Cart> itr = activeCartProducts.listIterator();
		List<CartResponse> cartResponseList = new ArrayList<CartResponse>();
		while (itr.hasNext()) {
			Cart currentCart = itr.next();
			cartResponse = new CartResponse();
			cartResponse.setProduct(currentCart.getProduct());
			cartResponse.setQuantity(currentCart.getQuantity());
			cartResponse.setUser(currentCart.getCustomer());
			cartResponse.setTotalProductPrice(currentCart.getProduct().getPrice() * currentCart.getQuantity());
			totalCartPrice = totalCartPrice + cartResponse.getTotalProductPrice();
			cartResponseList.add(cartResponse);
		}
		CartTotal cartTotal = new CartTotal();
		cartTotal.setCarts(cartResponseList);
		cartTotal.setTotalPrice(totalCartPrice);
		return cartTotal;
	}

	@Override
	public Cart update(Cart cart) {
		return createCart(cart);
	}


	@Override
	public Cart readyByProductAndCustomerAndStatus(Product product, User user, CartStatus status) {
		return cartRepository.findByProductAndCustomerAndStatus(product,user,CartStatus.ACTIVE);
	}

}
