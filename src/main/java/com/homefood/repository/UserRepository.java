package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.UserRole;
import com.homefood.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Serializable> {

	public User findByUserid(long id);

	public User findByEmail(String email);

	public User findByUserName(String userName);

	public User findByEmailAndPassword(String email, String password);

	public List<User> findByIsApprovedAndUserRole(boolean isApproved, UserRole role);

}
