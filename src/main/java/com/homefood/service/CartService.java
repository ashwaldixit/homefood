package com.homefood.service;

import java.util.List;

import com.homefood.codetype.CartStatus;
import com.homefood.model.Cart;
import com.homefood.model.CartTotal;
import com.homefood.model.Product;
import com.homefood.model.User;

public interface CartService {

	public Cart createCart(Cart cart);

	public Cart readById(long id);

	public List<Cart> readAllActiveByCustomer(User customer);

	public void processAllByCustomer(User customer);

	public Cart removeFromCart(Cart cart);

	public CartTotal computeCart(User customer);

	public Cart update(Cart cart);

	public Cart readyByProductAndCustomerAndStatus(Product product, User user, CartStatus status);

	public Cart addProductToCart(Product product, User user);

	public CartTotal removeProductFromCart(Product product, User user);

}
