package com.homefood.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homefood.codetype.CartStatus;
import com.homefood.codetype.NotificationInfo;
import com.homefood.core.TransactionInfo;
import com.homefood.model.Cart;
import com.homefood.model.CartResponse1;
import com.homefood.model.CartTotal;
import com.homefood.model.Product;
import com.homefood.model.User;
import com.homefood.repository.CartRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private TransactionInfo transactionInfo;

	@Override
	public Cart createCart(Cart cart) {
		Cart existingCart = cartRepository.findByProductAndCustomerAndStatus(cart.getProduct(), cart.getCustomer(),
				CartStatus.ACTIVE);

		if (null != existingCart) {
			existingCart.setQuantity(existingCart.getQuantity() + 1);
			return cartRepository.save(existingCart);
		}
		cart.setQuantity(1);

		List<Cart> result = readAllActiveByCustomer(cart.getCustomer());

		if (null != result && !result.isEmpty()) {
			if (!result.get(0).getProduct().getCaterer().equals(cart.getProduct().getCaterer())) {
				transactionInfo.generateRuntimeException("transactionInfo", NotificationInfo.ERROR,
						Status.INTERNAL_SERVER_ERROR.getStatusCode());
			}
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
		CartResponse1 cartResponse;
		ListIterator<Cart> itr = activeCartProducts.listIterator();
		List<CartResponse1> cartResponseList = new ArrayList<CartResponse1>();
		while (itr.hasNext()) {
			Cart currentCart = itr.next();
			cartResponse = new CartResponse1();
			cartResponse.setCart(currentCart);
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
		return cartRepository.findByProductAndCustomerAndStatus(product, user, CartStatus.ACTIVE);
	}

	@Override
	public Cart addProductToCart(Product product, User user) {
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setCustomer(user);
		return createCart(cart);
	}

	@Override
	public CartTotal removeProductFromCart(Product product, User user) {
		Cart existingCart = cartRepository.findByProductAndCustomerAndStatus(product, user, CartStatus.ACTIVE);
		if (existingCart.getQuantity() <= 1) {
			existingCart.setStatus(CartStatus.REMOVED);
		} else {
			existingCart.setQuantity(existingCart.getQuantity() - 1);

		}
		return computeCart(user);
	}

}
