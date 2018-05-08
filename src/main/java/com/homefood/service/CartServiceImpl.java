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
		Map<Product, Integer> productQtyMap = new HashMap<Product, Integer>();
		Integer quantity = 1;
		double totalCartPrice = 0;
		ListIterator<Cart> itr = activeCartProducts.listIterator();
		while (itr.hasNext()) {
			Cart currentCart = itr.next();
			if (!productQtyMap.containsKey(currentCart.getProduct())) {
				productQtyMap.put(currentCart.getProduct(), quantity);
			} else {
				productQtyMap.replace(currentCart.getProduct(), productQtyMap.get(currentCart.getProduct()) + 1);
			}
		}
		CartResponse cartResponse;
		CartTotal cartTotal = new CartTotal();
		List<CartResponse> cartResponseList = new ArrayList<CartResponse>();
		for (Entry<Product, Integer> cartProduct : productQtyMap.entrySet()) {
			cartResponse = new CartResponse();
			cartResponse.setProduct(cartProduct.getKey());
			cartResponse.setQuantity(cartProduct.getValue());
			cartResponse.setUser(customer);
			cartResponse.setTotalProductPrice(cartProduct.getValue() * cartProduct.getKey().getPrice());
			totalCartPrice = totalCartPrice + cartResponse.getTotalProductPrice();
			cartResponseList.add(cartResponse);
		}
		cartTotal.setCarts(cartResponseList);
		cartTotal.setTotalPrice(totalCartPrice);
		return cartTotal;
	}

}
