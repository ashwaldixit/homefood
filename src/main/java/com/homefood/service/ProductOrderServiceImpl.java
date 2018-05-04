package com.homefood.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.CustomerOrder;
import com.homefood.model.Product;
import com.homefood.model.ProductOrder;
import com.homefood.repository.ProductOrderRepository;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	ProductOrderRepository orderRepository;
	@Autowired
	UserService customerService;

	@Autowired
	CustomerOrderService customerOrderService;

	@Override
	public ProductOrder createOrder(ProductOrder order) {
		CustomerOrder customerOrder = customerOrderService.readById(order.getCustomerOrder().getCustomerorderid());
		if (null == customerOrder) {
			customerOrder = customerOrderService.validateAndCreate(customerOrder);
		}
		order.setCustomerOrder(customerOrder);
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
