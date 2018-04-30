package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Caterer;

@Repository
public interface CatererRepository extends CrudRepository<Caterer, Serializable> {

	public Caterer findByCatererid(long id);

	public List<Caterer> findByNameAndRecordStatus(String name, RecordStatus recordStatus);

}
