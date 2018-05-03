package com.homefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Customer;
import com.homefood.model.CustomerOrder;
import com.homefood.model.ProductOrder;
import com.homefood.repository.CustomerOrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	CustomerOrderRepository customerOrderRepository;

	@Autowired
	CustomerService customerService;

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
		Customer customer = customerService.readById(customerOrder.getCustomer().getCustomerid());
		if (null == customer) {
			customer = customerService.createCustomer(customerOrder.getCustomer());
		}
		customerOrder.setCustomer(customer);
		return customerOrderRepository.save(customerOrder);
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
	public List<CustomerOrder> readAllByCustomer(Customer customer) {
		return customerOrderRepository.findByCustomer(customer);
	}

	@Override
	public List<CustomerOrder> readAllByCustomerAndStatus(Customer customer, OrderStatus orderStatus) {
		return customerOrderRepository.findByCustomerAndStatus(customer, orderStatus);
	}

	@Override
	public List<CustomerOrder> readAllOpenOrdersOfCustomer(Customer customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Open);
	}

	@Override
	public List<CustomerOrder> readAllConfirmedOrdersOfCustomer(Customer customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Confirmed);
	}

	@Override
	public List<CustomerOrder> readAllDeliveredOrdersOfCustomer(Customer customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Delivered);
	}

	@Override
	public List<CustomerOrder> readAllCancelledOrdersOfCustomer(Customer customer) {
		return customerOrderRepository.findByCustomerAndStatus(customer, OrderStatus.Cancelled);
	}

}
