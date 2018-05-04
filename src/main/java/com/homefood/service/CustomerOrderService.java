package com.homefood.service;

import java.util.List;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.User;
import com.homefood.model.CustomerOrder;


public interface CustomerOrderService {

	public CustomerOrder readById(long id);

	public CustomerOrder validateAndCreate(CustomerOrder customerOrder);

	public void validate(CustomerOrder customerOrder);

	public CustomerOrder create(CustomerOrder customerOrder);

	public CustomerOrder update(CustomerOrder customerOrder);

	public List<CustomerOrder> readAllByStatus(RecordStatus recordStatus);

	public List<CustomerOrder> readAllByCustomer(User customer);

	public List<CustomerOrder> readAllByCustomerAndStatus(User customer, OrderStatus orderStatus);

	public List<CustomerOrder> readAllOpenOrdersOfCustomer(User customer);

	public List<CustomerOrder> readAllConfirmedOrdersOfCustomer(User customer);

	public List<CustomerOrder> readAllDeliveredOrdersOfCustomer(User customer);

	public List<CustomerOrder> readAllCancelledOrdersOfCustomer(User customer);

}
