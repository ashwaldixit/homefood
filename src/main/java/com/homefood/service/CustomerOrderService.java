package com.homefood.service;

import java.util.List;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Customer;
import com.homefood.model.CustomerOrder;


public interface CustomerOrderService {

	public CustomerOrder readById(long id);

	public CustomerOrder validateAndCreate(CustomerOrder customerOrder);

	public void validate(CustomerOrder customerOrder);

	public CustomerOrder create(CustomerOrder customerOrder);

	public CustomerOrder update(CustomerOrder customerOrder);

	public List<CustomerOrder> readAllByStatus(RecordStatus recordStatus);

	public List<CustomerOrder> readAllByCustomer(Customer customer);

	public List<CustomerOrder> readAllByCustomerAndStatus(Customer customer, OrderStatus orderStatus);

	public List<CustomerOrder> readAllOpenOrdersOfCustomer(Customer customer);

	public List<CustomerOrder> readAllConfirmedOrdersOfCustomer(Customer customer);

	public List<CustomerOrder> readAllDeliveredOrdersOfCustomer(Customer customer);

	public List<CustomerOrder> readAllCancelledOrdersOfCustomer(Customer customer);

}
