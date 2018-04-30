package com.homefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Product;
import com.homefood.model.ProductPresence;
import com.homefood.repository.ProductPresenceRepository;

@Service
@Transactional
public class ProductPresenceServiceImpl implements ProductPresenceService {

	@Autowired
	ProductPresenceRepository productPresenceRepository;

	@Autowired
	ProductService productService;

	@Override
	public ProductPresence createProductPresence(ProductPresence productPresence) {

		Product product = productService.readById(productPresence.getProduct().getProductid());
		if (null == product)
			productService.createProduct(product);
		productPresence.setProduct(product);

		return productPresenceRepository.save(productPresence);
	}

	@Override
	public ProductPresence readById(long id) {
		return productPresenceRepository.findByProdsectionid(id);
	}

	@Override
	public List<ProductPresence> readAllInactiveByProduct(Product product) {
		return productPresenceRepository.findByProductAndRecordStatus(product, RecordStatus.InActive);
	}

	@Override
	public ProductPresence readActiveByProduct(Product product) {
		List<ProductPresence> result = productPresenceRepository.findByProductAndRecordStatus(product,
				RecordStatus.Active);
		if (null != result && !result.isEmpty())
			return result.get(0);
		return null;
	}

	@Override
	public void validate(ProductPresence productPresence) {

	}

	@Override
	public ProductPresence validateAndCreate(ProductPresence productPresence) {
		validate(productPresence);
		return createProductPresence(productPresence);

	}

	@Override
	public ProductPresence update(ProductPresence productPresence) {
		return createProductPresence(productPresence);
	}

}
