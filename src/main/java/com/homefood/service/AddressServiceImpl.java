package com.homefood.service;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.NotificationInfo;
import com.homefood.codetype.RecordStatus;
import com.homefood.core.TransactionInfo;
import com.homefood.model.Address;
import com.homefood.model.User;
import com.homefood.repository.AddressRepository;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserService userService;

	@Autowired
	TransactionInfo transactionInfo;

	@Override
	public Address createAddress(Address address) {
		User user = userService.readById(address.getUser().getUserid());
		if (null == user)
			user = userService.validateAndCreateCustomer(user);
		address.setUser(user);

		return addressRepository.save(address);
	}

	@Override
	public Address validateAndCreate(Address address) {
		validate(address);
		return createAddress(address);
	}

	@Override
	public void validate(Address address) {
		// TODO Auto-generated method stub

	}

	@Override
	public Address update(Address address) {

		if (null != readById(address.getAddressid()))
			return createAddress(address);

		transactionInfo.generateException("UPDATE_FAILED", NotificationInfo.ERROR,
				Status.INTERNAL_SERVER_ERROR.getStatusCode());
		return null;
	}

	@Override
	public Address readById(long id) {
		return addressRepository.readByAddressid(id);
	}

	@Override
	public Address getActiveAndDefaultByUser(User user) {

		List<Address> result = addressRepository.findByUserAndIsDefaultAndStatus(user, true, RecordStatus.Active);

		if (null != result && !result.isEmpty())
			return result.get(0);
		return null;
	}

	@Override
	public List<Address> getAllNonDefaultByAddressByUser(User user) {
		return addressRepository.findByUserAndIsDefaultAndStatus(user, false, RecordStatus.Active);
	}

	@Override
	public Address setAsDefault(Address address) {
		Address defaultAddres  = getActiveAndDefaultByUser(address.getUser());
		defaultAddres.setIsDefault(false);
		addressRepository.save(defaultAddres);
		address.setIsDefault(true);
		return update(address);
	}


}
