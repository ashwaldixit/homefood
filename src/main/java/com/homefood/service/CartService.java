package com.homefood.service;

import java.util.List;

import com.homefood.model.Cart;
import com.homefood.model.Customer;

public interface CartService {

	public Cart createCart(Cart cart);
	public Cart readById(long id);
	public List<Cart> readAllActiveByCustomer(Customer customer);
	public void processAllByCustomer(Customer customer);
	public Cart removeFromCart(Cart cart);
}
