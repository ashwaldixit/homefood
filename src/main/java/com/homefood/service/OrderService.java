package com.homefood.service;

import java.time.LocalDateTime;
import java.util.List;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Customer;
import com.homefood.model.Order;
import com.homefood.model.Product;

public interface OrderService {

	public Order createOrder(Order order);

	public Order readById(long id);

	public void validate(Order order);

	public Order validateAndCreate(Order order);

	public Order update(Order order);

	public List<Order> readByCustomerAndStatus(Customer customer, OrderStatus orderStatus);

	public List<Order> readAllOpenOrdersOfCustomer(Customer customer);

	public List<Order> readAllConfirmedOrdersOfCustomer(Customer customer);

	public List<Order> readAllDeliveredOrdersOfCustomer(Customer customer);

	public List<Order> readAllCancelledOrdersOfCustomer(Customer customer);

	public List<Order> readAllByCustomerAndProduct(Customer customer, Product product);

	public List<Order> readdAllByProductAndStatus(Product product, RecordStatus recordStatus);

	public List<Order> readAllActiveByProduct(Product product);

	public List<Order> readAllInActiveByProduct(Product product);

	public List<Order> readAllByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

	public List<Order> readAllByDeliveryDate(LocalDateTime deliveryDate);

	public List<Order> readAllConfirmedByDeliveryDate(LocalDateTime deliveryDate);

	public List<Order> readAllDeliveredByDeliveryDate(LocalDateTime deliveryDate);

	public List<Order> readAllOpenByDeliveryDate(LocalDateTime deliveryDate);

	public List<Order> readAllCancelledByDeliveryDate(LocalDateTime deliveryDate);

	public List<Order> readAllByProductAndDeliveryDateAndOrderStatus(Product product, LocalDateTime deliveryDate,
			OrderStatus orderStatus);

	public List<Order> readAllConfirmedByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

	public List<Order> readAllDeliveredByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

	public List<Order> readAllCancelledByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

	public List<Order> readAllOpenByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

}
