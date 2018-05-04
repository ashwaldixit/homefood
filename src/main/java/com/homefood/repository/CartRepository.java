package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.CartStatus;
import com.homefood.model.Cart;
import com.homefood.model.Customer;

@Repository
public interface CartRepository extends JpaRepository<Cart, Serializable> {

	public Cart readByCartid(long cartd);

	public List<Cart> findByCustomerAndStatus(Customer customer, CartStatus status);

	@Modifying
	@Transactional
	@Query("update cart a set a.status = :status where a.customer = :customer")
	public int updateStatusByCustomer(@Param("status") CartStatus status, @Param("customer") Customer customer);

}
