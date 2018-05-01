package com.homefood.service;

import com.homefood.model.Customer;

public interface CustomerService {

	public Customer readById(long id);

	public Customer createCustomer(Customer customer);

	public void validate(Customer customer);

	public Customer validateAndCreateCustomer(Customer customer);

	public Customer update(Customer customer);

	public Customer readByEmail(String email);

	public Customer readByUserName(String userName);

}
