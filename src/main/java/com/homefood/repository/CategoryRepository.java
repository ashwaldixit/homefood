package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Serializable> {

	public Category findByCategoryid(long id);

	public List<Category> findByNameAndRecordStatus(String name, RecordStatus status);

	public List<Category> findByRecordStatus(RecordStatus recordStatus);

}
