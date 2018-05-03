package com.homefood.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Serializable> {

	public Customer findByCustomerid(long id);

	public Customer findByEmail(String email);

	public Customer findByUserName(String userName);

}
