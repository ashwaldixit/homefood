package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Product;
import com.homefood.model.ProductPresence;

@Repository
public interface ProductPresenceRepository extends CrudRepository<ProductPresence, Serializable> {

	public ProductPresence findByProdsectionid(long id);

	public List<ProductPresence> findByProductAndRecordStatus(Product product, RecordStatus recordStatus);
}
