package com.homefood.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.homefood.model.Address;
import com.homefood.model.User;

public interface AddressService {

	public Address createAddress(Address address) throws NoSuchAlgorithmException;

	public Address validateAndCreate(Address address);

	public void validate(Address address);

	public Address update(Address address);

	public Address readById(long id);

	public Address getActiveAndDefaultByUser(User user);

	public List<Address> getAllNonDefaultByAddressByUser(User user);

	public Address setAsDefault(Address address);

}
