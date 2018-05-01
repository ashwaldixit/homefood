package com.homefood.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Customer;
import com.homefood.model.Order;
import com.homefood.model.Product;
import com.homefood.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerService customerService;

	@Override
	public Order createOrder(Order order) {
		Customer customer = customerService.readById(order.getCustomer().getCustomerid());
		if (null == customer)
			customer = customerService.createCustomer(order.getCustomer());
		order.setCustomer(customer);

		return orderRepository.save(order);
	}

	@Override
	public Order readById(long id) {
		return orderRepository.findByOrderid(id);
	}

	@Override
	public void validate(Order order) {

	}

	@Override
	public Order validateAndCreate(Order order) {
		validate(order);
		return createOrder(order);
	}

	@Override
	public Order update(Order order) {
		return createOrder(order);
	}

	@Override
	public List<Order> readByCustomerAndStatus(Customer customer, OrderStatus orderStatus) {
		return orderRepository.findByCustomerAndOrderStatus(customer, orderStatus);
	}

	@Override
	public List<Order> readAllOpenOrdersOfCustomer(Customer customer) {
		return orderRepository.findByCustomerAndOrderStatus(customer, OrderStatus.Open);
	}

	@Override
	public List<Order> readAllConfirmedOrdersOfCustomer(Customer customer) {
		return orderRepository.findByCustomerAndOrderStatus(customer, OrderStatus.Confirmed);

	}

	@Override
	public List<Order> readAllDeliveredOrdersOfCustomer(Customer customer) {
		return orderRepository.findByCustomerAndOrderStatus(customer, OrderStatus.Delivered);

	}

	@Override
	public List<Order> readAllCancelledOrdersOfCustomer(Customer customer) {
		return orderRepository.findByCustomerAndOrderStatus(customer, OrderStatus.Cancelled);

	}

	@Override
	public List<Order> readAllByCustomerAndProduct(Customer customer, Product product) {
		return orderRepository.findByCustomerAndProduct(customer, product);
	}

	@Override
	public List<Order> readdAllByProductAndStatus(Product product, RecordStatus recordStatus) {
		return orderRepository.findByProductAndStatus(product, recordStatus);
	}

	@Override
	public List<Order> readAllActiveByProduct(Product product) {
		return orderRepository.findByProductAndStatus(product, RecordStatus.Active);
	}

	@Override
	public List<Order> readAllInActiveByProduct(Product product) {
		return orderRepository.findByProductAndStatus(product, RecordStatus.InActive);
	}

	@Override
	public List<Order> readAllByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydate(product, deliveryDate);
	}

	@Override
	public List<Order> readAllByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydate(deliveryDate);
	}

	@Override
	public List<Order> readAllConfirmedByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydateAndOrderStatus(deliveryDate, OrderStatus.Confirmed);
	}

	@Override
	public List<Order> readAllDeliveredByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydateAndOrderStatus(deliveryDate, OrderStatus.Delivered);
	}

	@Override
	public List<Order> readAllOpenByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydateAndOrderStatus(deliveryDate, OrderStatus.Open);
	}

	@Override
	public List<Order> readAllCancelledByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydateAndOrderStatus(deliveryDate, OrderStatus.Cancelled);
	}

	@Override
	public List<Order> readAllByProductAndDeliveryDateAndOrderStatus(Product product, LocalDateTime deliveryDate,
			OrderStatus orderStatus) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, orderStatus);
	}

	@Override
	public List<Order> readAllConfirmedByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, OrderStatus.Confirmed);
	}

	@Override
	public List<Order> readAllDeliveredByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, OrderStatus.Delivered);
	}

	@Override
	public List<Order> readAllCancelledByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, OrderStatus.Cancelled);
	}

	@Override
	public List<Order> readAllOpenByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, OrderStatus.Open);
	}

}
