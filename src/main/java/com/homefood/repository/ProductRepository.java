package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Product;
import com.homefood.model.ProductPresence;

@Repository
public interface ProductRepository extends CrudRepository<Product, Serializable> {

	public Product findByProductid(long id);

	public List<Product> findByNameAndStatus(String name, RecordStatus status);

	public List<Product> findByCategoryAndStatus(Category category, RecordStatus status);

	public List<Product> findByCatererAndStatus(Caterer caterer, RecordStatus status);

	public List<Product> findByPresenceAndStatus(ProductPresence presence, RecordStatus status);

	public List<Product> findByNameAndCatererAndStatus(String name, Caterer caterer, RecordStatus status);

	public List<Product> findByCategoryAndCatererAndStatus(Category category, Caterer caterer, RecordStatus status);

}
