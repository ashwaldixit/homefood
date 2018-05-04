package com.homefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.model.User;
import com.homefood.repository.CustomerRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public User readById(long id) {
		return customerRepository.findByUserid(id);
	}

	@Override
	public User createCustomer(User customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void validate(User customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public User validateAndCreateCustomer(User customer) {
		validate(customer);
		return createCustomer(customer);
	}

	@Override
	public User update(User customer) {
		return createCustomer(customer);
	}

	@Override
	public User readByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public User readByUserName(String userName) {
		return customerRepository.findByUserName(userName);
	}

}
