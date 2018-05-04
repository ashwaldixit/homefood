package com.homefood.service;

import java.util.List;

import com.homefood.model.Address;
import com.homefood.model.User;

public interface AddressService {

	public Address createAddress(Address address);

	public Address validateAndCreate(Address address);

	public void validate(Address address);

	public Address update(Address address);

	public Address readById(long id);

	public Address getActiveAndDefaultByUser(User user);

	public List<Address> getAllNonDefaultByAddressByUser(User user);

	public Address setAsDefault(Address address);

}
