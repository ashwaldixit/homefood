package com.homefood.model;

import java.util.List;

public class CartTotal {

	private List<CartResponse1> carts;

	private double totalPrice;

	public List<CartResponse1> getCarts() {
		return carts;
	}

	public void setCarts(List<CartResponse1> carts) {
		this.carts = carts;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
