package com.homefood.model;

public class CartResponse1 {
	private Cart cart;
	private double totalProductPrice;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public double getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}
}
