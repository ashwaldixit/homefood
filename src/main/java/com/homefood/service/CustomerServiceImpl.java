package com.homefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.model.Customer;
import com.homefood.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer readById(long id) {
		return customerRepository.findByCustomerid(id);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void validate(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer validateAndCreateCustomer(Customer customer) {
		validate(customer);
		return createCustomer(customer);
	}

	@Override
	public Customer update(Customer customer) {
		return createCustomer(customer);
	}

	@Override
	public Customer readByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public Customer readByUserName(String userName) {
		return customerRepository.findByUserName(userName);
	}

}
