package com.homefood.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.UserRole;
import com.homefood.core.EmailService;
import com.homefood.core.PasswordMasker;
import com.homefood.model.Caterer;
import com.homefood.model.User;
import com.homefood.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository customerRepository;

	@Autowired
	private PasswordMasker passwordMasker;

	@Autowired
	private AddressService addressService;

	@Autowired
	private CatererService catererService;

	@Autowired
	private EmailService emailService;

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

	}

	@Override
	public User validateAndCreateCustomer(User customer) throws NoSuchAlgorithmException {
		if (null != customer) {
			validate(customer);
			customer.setPassword(getHashedPassword(customer));
			if (!customer.getUserRole().equals(UserRole.Caterer)) {
				customer.setIsApproved(true);

			} else if (customer.getUserRole().equals(UserRole.Caterer)) {
				Caterer caterer = new Caterer();
				caterer.setUser(customer);
				caterer.setName(customer.getUserName());
				caterer.setDescription(customer.getUserName());
				catererService.validateAndCreate(caterer);
			}
			customer = createCustomer(customer);
			createAddresses(customer);
			Map<String, Object> model = new HashMap<>();
			model.put("user", customer);
			emailService.sendEmail(customer, customer.getEmail(), "Successfully Registered",
					"/templates/email/EmailRegistration.vm", model);
			return customer;
		}
		return null;
	}

	private String getHashedPassword(User user) throws NoSuchAlgorithmException {
		return passwordMasker.getHashedPassword(user.getPassword());
	}

	@Override
	public User update(User customer) throws NoSuchAlgorithmException {
		customer.setPassword(readById(customer.getUserid()).getPassword());
		return validateAndCreateCustomer(customer);
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

	@Override
	public List<User> getAllNonApprovedMerchants() {
		return customerRepository.findByIsApprovedAndUserRole(false, UserRole.Caterer);
	}

	private void createAddresses(User user) {
		user.getAddresses().stream().forEach(address -> {
			address.setUser(user);
			this.addressService.validateAndCreate(address);
		});

	}

}
