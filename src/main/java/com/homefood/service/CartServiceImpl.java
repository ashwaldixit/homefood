package com.homefood.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homefood.codetype.CartStatus;
import com.homefood.model.Cart;
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

}
