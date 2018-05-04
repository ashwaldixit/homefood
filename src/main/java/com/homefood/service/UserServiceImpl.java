package com.homefood.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.core.PasswordMasker;
import com.homefood.model.User;
import com.homefood.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository customerRepository;

	@Autowired
	private PasswordMasker passwordMasker;

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
	public User validateAndCreateCustomer(User customer) throws NoSuchAlgorithmException {
		validate(customer);
		customer.setPassword(getHashedPassword(customer));
		return createCustomer(customer);
	}

	private String getHashedPassword(User user) throws NoSuchAlgorithmException {
		return passwordMasker.getHashedPassword(user.getPassword());
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

	@Override
	public User findByEmailAndPassword(String email, String password) {
		return customerRepository.findByEmailAndPassword(email, password);
	}

}
