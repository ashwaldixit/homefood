package com.homefood.service;

import java.util.List;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Product;
import com.homefood.model.ProductPresence;

public interface ProductService {

	public Product createProduct(Product product);

	public Product readById(long id);

	public void validate(Product product);

	public List<Product> readAllByNameAndStatus(String name, RecordStatus status);

	public List<Product> readAllActiveByCategory(Category category);

	public List<Product> readAllInActiveByCategory(Category category);

	public List<Product> readAllActiveByCaterer(Caterer caterer);

	public List<Product> readAllInActiveByCaterer(Caterer caterer);

	public List<Product> readAllByPresenceAndStatus(ProductPresence presence, RecordStatus status);

	public List<Product> readAllActiveByNameAndCaterer(String name, Caterer caterer);

	public List<Product> readAllInActiveByNameAndCaterer(String name, Caterer caterer);

	public List<Product> readAllActiveByCategoryAndCaterer(Category category, Caterer caterer);

	public List<Product> readAllInActiveByCategoryAndCaterer(Category category, Caterer caterer);

}
