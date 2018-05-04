package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Address;
import com.homefood.model.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Serializable> {

	public Address readByAddressid(long id);

	public List<Address> findByUserAndIsDefaultAndStatus(User user, boolean isDefault,RecordStatus status);

	public List<Address> findByuserAndStatus(User user, RecordStatus status);

}
