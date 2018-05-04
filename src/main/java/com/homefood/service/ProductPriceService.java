package com.homefood.service;

import java.util.List;

import com.homefood.model.Product;
import com.homefood.model.ProductPrice;

public interface ProductPriceService {

	public ProductPrice createProductPrice(ProductPrice productPrice);

	public ProductPrice readById(long id);

	public ProductPrice getActiveByProduct(Product product);

	public List<ProductPrice> getAllInactiveByProduct(Product product);

	public List<ProductPrice> getByProduct(Product product);

	public ProductPrice update(ProductPrice productPrice);

}
