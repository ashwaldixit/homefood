package com.homefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Product;
import com.homefood.model.ProductPresence;
import com.homefood.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		// TODO Detached entity logic
		return productRepository.save(product);
	}

	@Override
	public Product readById(long id) {
		return productRepository.findByProductid(id);
	}

	@Override
	public void validate(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> readAllByNameAndStatus(String name, RecordStatus status) {
		return productRepository.findByNameAndStatus(name, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllActiveByCategory(Category category) {
		return productRepository.findByCategoryAndStatus(category, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByCategory(Category category) {
		return productRepository.findByCategoryAndStatus(category, RecordStatus.InActive);
	}

	@Override
	public List<Product> readAllActiveByCaterer(Caterer caterer) {
		return productRepository.findByCatererAndStatus(caterer, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByCaterer(Caterer caterer) {
		return productRepository.findByCatererAndStatus(caterer, RecordStatus.InActive);
	}

	@Override
	public List<Product> readAllByPresenceAndStatus(ProductPresence presence, RecordStatus status) {
		return productRepository.findByPresenceAndStatus(presence, status);
	}

	@Override
	public List<Product> readAllActiveByNameAndCaterer(String name, Caterer caterer) {
		return productRepository.findByNameAndCatererAndStatus(name, caterer, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByNameAndCaterer(String name, Caterer caterer) {
		return productRepository.findByNameAndCatererAndStatus(name, caterer, RecordStatus.InActive);
	}

	@Override
	public List<Product> readAllActiveByCategoryAndCaterer(Category category, Caterer caterer) {
		return productRepository.findByCategoryAndCatererAndStatus(category, caterer, RecordStatus.Active);
	}

	@Override
	public List<Product> readAllInActiveByCategoryAndCaterer(Category category, Caterer caterer) {
		return productRepository.findByCategoryAndCatererAndStatus(category, caterer, RecordStatus.InActive);
	}

}
