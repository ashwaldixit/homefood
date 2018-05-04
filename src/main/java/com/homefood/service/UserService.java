package com.homefood.service;

import com.homefood.model.User;

public interface UserService {

	public User readById(long id);

	public User createCustomer(User customer);

	public void validate(User customer);

	public User validateAndCreateCustomer(User customer);

	public User update(User customer);

	public User readByEmail(String email);

	public User readByUserName(String userName);

}
