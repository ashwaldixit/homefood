package com.homefood.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.User;
import com.homefood.model.Cart;
import com.homefood.model.CustomerOrder;
import com.homefood.model.ProductOrder;
import com.homefood.repository.CustomerOrderRepository;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	CustomerOrderRepository customerOrderRepository;

	@Autowired
	UserService customerService;

	@Autowired
	CartService cartService;

	@Autowired
	ProductOrderService productOrderService;

	@Override
	public CustomerOrder readById(long id) {
		return customerOrderRepository.findByCustomerorderid(id);
	}

	@Override
	public CustomerOrder validateAndCreate(CustomerOrder customerOrder) {
		validate(customerOrder);
		return create(customerOrder);
	}

	@Override
	public void validate(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomerOrder create(CustomerOrder customerOrder) {
		User customer = customerService.readById(customerOrder.getCustomer().getUserid());
		if (null == customer) {
			customer = customerService.createCustomer(customerOrder.getCustomer());
		}
		customerOrder.setCustomer(customer);
		customerOrder = customerOrderRepository.save(customerOrder);
		final CustomerOrder customerOrder2 = customerOrder;
		List<Cart> carts = cartService.readAllActiveByCustomer(customerOrder.getCustomer());
		carts.stream().forEach(cart -> {
			ProductOrder productOrder = new ProductOrder();
			productOrder.setCustomerOrder(customerOrder2);
			productOrder.setDeliverydate(LocalDateTime.now().plusDays(1));
			productOrder.setProduct(cart.getProduct());
			productOrderService.validateAndCreate(productOrder);
		});

		cartService.processAllByCustomer(customerOrder.getCustomer());
		return customerOrder;
	}

	@Override
	public CustomerOrder update(CustomerOrder customerOrder) {
		return create(customerOrder);
	}

	@Override
	public List<CustomerOrder> readAllByStatus(RecordStatus recordStatus) {
		return customerOrderRepository.findByStatus(recordStatus);
	}

	@Override
	public List<CustomerOrder> readAllByCustomer(User customer) {
		return customerOrderRepository.findByCustomer(customer);
	}

	@Override
	public List<CustomerOrder> readAllByCustomerAndStatus(User customer, OrderStatus orderStatus) {
		return customerOrderRepository.findByCustomerAndStatus(customer, orderStatus);
	}

	@Override
	public List<CustomerOrder> readAllOpenOrdersOfCustomer(User customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Open);
	}

	@Override
	public List<CustomerOrder> readAllConfirmedOrdersOfCustomer(User customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Confirmed);
	}

	@Override
	public List<CustomerOrder> readAllDeliveredOrdersOfCustomer(User customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Delivered);
	}

	@Override
	public List<CustomerOrder> readAllCancelledOrdersOfCustomer(User customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Cancelled);
	}

}
