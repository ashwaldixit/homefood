package com.homefood.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Serializable> {

}
