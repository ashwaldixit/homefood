package com.homefood.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Customer;
import com.homefood.model.Order;
import com.homefood.model.Product;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {

	public Order findByOrderid(long id);

	public List<Order> findByCustomer(Customer customer);

	public List<Order> findByCustomerAndOrderStatus(Customer customer, OrderStatus orderStatus);

	public List<Order> findByCustomerAndProduct(Customer customer, Product product);

	public List<Order> findByProductAndStatus(Product product, RecordStatus recordStatus);

	public List<Order> findByProductAndDeliverydate(Product product, LocalDateTime deliveryDate);

	public List<Order> findByDeliverydate(LocalDateTime deliveryDate);

	public List<Order> findByDeliverydateAndOrderStatus(LocalDateTime deliveryDate, OrderStatus orderStatus);

	public List<Order> findByProductAndDeliverydateAndOrderStatus(Product product, LocalDateTime deliveryDate,
			OrderStatus orderStatus);
}
