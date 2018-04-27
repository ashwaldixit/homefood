package com.homefood.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.model.CategoryProductSection;

@Repository
public interface CategoryProductSectionRepository extends CrudRepository<CategoryProductSection, Serializable> {

}
