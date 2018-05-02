package com.homefood.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Customer;
import com.homefood.model.ProductOrder;
import com.homefood.model.Product;
import com.homefood.repository.ProductOrderRepository;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	ProductOrderRepository orderRepository;
	@Autowired
	CustomerService customerService;

	@Override
	public ProductOrder createOrder(ProductOrder order) {
		Customer customer = customerService.readById(order.getCustomer().getCustomerid());
		if (null == customer)
			customer = customerService.createCustomer(order.getCustomer());
		order.setCustomer(customer);

		return orderRepository.save(order);
	}

	@Override
	public ProductOrder readById(long id) {
		return orderRepository.findByOrderid(id);
	}

	@Override
	public void validate(ProductOrder order) {

	}

	@Override
	public ProductOrder validateAndCreate(ProductOrder order) {
		validate(order);
		return createOrder(order);
	}

	@Override
	public ProductOrder update(ProductOrder order) {
		return createOrder(order);
	}

	@Override
	public List<ProductOrder> readByCustomerAndStatus(Customer customer, OrderStatus orderStatus) {
		return orderRepository.findByCustomerAndOrderStatus(customer, orderStatus);
	}

	@Override
	public List<ProductOrder> readAllOpenOrdersOfCustomer(Customer customer) {
		return orderRepository.findByCustomerAndOrderStatus(customer, OrderStatus.Open);
	}

	@Override
	public List<ProductOrder> readAllConfirmedOrdersOfCustomer(Customer customer) {
		return orderRepository.findByCustomerAndOrderStatus(customer, OrderStatus.Confirmed);

	}

	@Override
	public List<ProductOrder> readAllDeliveredOrdersOfCustomer(Customer customer) {
		return orderRepository.findByCustomerAndOrderStatus(customer, OrderStatus.Delivered);

	}

	@Override
	public List<ProductOrder> readAllCancelledOrdersOfCustomer(Customer customer) {
		return orderRepository.findByCustomerAndOrderStatus(customer, OrderStatus.Cancelled);

	}

	@Override
	public List<ProductOrder> readAllByCustomerAndProduct(Customer customer, Product product) {
		return orderRepository.findByCustomerAndProduct(customer, product);
	}

	@Override
	public List<ProductOrder> readdAllByProductAndStatus(Product product, RecordStatus recordStatus) {
		return orderRepository.findByProductAndStatus(product, recordStatus);
	}

	@Override
	public List<ProductOrder> readAllActiveByProduct(Product product) {
		return orderRepository.findByProductAndStatus(product, RecordStatus.Active);
	}

	@Override
	public List<ProductOrder> readAllInActiveByProduct(Product product) {
		return orderRepository.findByProductAndStatus(product, RecordStatus.InActive);
	}

	@Override
	public List<ProductOrder> readAllByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydate(product, deliveryDate);
	}

	@Override
	public List<ProductOrder> readAllByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydate(deliveryDate);
	}

	@Override
	public List<ProductOrder> readAllConfirmedByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydateAndOrderStatus(deliveryDate, OrderStatus.Confirmed);
	}

	@Override
	public List<ProductOrder> readAllDeliveredByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydateAndOrderStatus(deliveryDate, OrderStatus.Delivered);
	}

	@Override
	public List<ProductOrder> readAllOpenByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydateAndOrderStatus(deliveryDate, OrderStatus.Open);
	}

	@Override
	public List<ProductOrder> readAllCancelledByDeliveryDate(LocalDateTime deliveryDate) {
		return orderRepository.findByDeliverydateAndOrderStatus(deliveryDate, OrderStatus.Cancelled);
	}

	@Override
	public List<ProductOrder> readAllByProductAndDeliveryDateAndOrderStatus(Product product, LocalDateTime deliveryDate,
			OrderStatus orderStatus) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, orderStatus);
	}

	@Override
	public List<ProductOrder> readAllConfirmedByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, OrderStatus.Confirmed);
	}

	@Override
	public List<ProductOrder> readAllDeliveredByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, OrderStatus.Delivered);
	}

	@Override
	public List<ProductOrder> readAllCancelledByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, OrderStatus.Cancelled);
	}

	@Override
	public List<ProductOrder> readAllOpenByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate) {
		return orderRepository.findByProductAndDeliverydateAndOrderStatus(product, deliveryDate, OrderStatus.Open);
	}

}
