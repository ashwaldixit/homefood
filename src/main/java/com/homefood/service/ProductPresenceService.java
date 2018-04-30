package com.homefood.service;

import java.util.List;

import com.homefood.model.Product;
import com.homefood.model.ProductPresence;

public interface ProductPresenceService {

	public ProductPresence createProductPresence(ProductPresence productPresence);

	public ProductPresence readById(long id);

	public List<ProductPresence> readAllInactiveByProduct(Product product);

	public ProductPresence readActiveByProduct(Product product);

	public void validate(ProductPresence productPresence);

	public ProductPresence validateAndCreate(ProductPresence productPresence);

	public ProductPresence update(ProductPresence productPresence);
}
