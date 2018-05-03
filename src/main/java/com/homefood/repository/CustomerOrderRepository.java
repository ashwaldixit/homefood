package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Customer;
import com.homefood.model.CustomerOrder;

@Repository
public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Serializable> {

	public CustomerOrder findByCustomerorderid(long id);

	public List<CustomerOrder> findByStatus(RecordStatus recordStatus);

	public List<CustomerOrder> findByCustomer(Customer customer);

	public List<CustomerOrder> findByCustomerAndStatus(Customer customer, OrderStatus orderStatus);

}
