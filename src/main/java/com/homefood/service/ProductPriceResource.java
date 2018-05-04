package com.homefood.service;

import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.NotificationInfo;
import com.homefood.codetype.RecordStatus;
import com.homefood.core.TransactionInfo;
import com.homefood.model.Product;
import com.homefood.model.ProductPrice;
import com.homefood.repository.ProductPriceRepository;

@Service
@Transactional
public class ProductPriceResource implements ProductPriceService {

	@Autowired
	ProductPriceRepository productPriceRepository;

	@Autowired
	ProductService productService;

	@Autowired
	TransactionInfo transactionInfo;

	@Override
	public ProductPrice createProductPrice(ProductPrice productPrice) {
		ProductPrice result = getActiveByProduct(productPrice.getProduct());
		if (null != result) {
			result.setStatus(RecordStatus.InActive);
			productPriceRepository.save(result);
		}

		Product prod = productService.readById(productPrice.getProduct().getProductid());
		if (null == prod)
			prod = productService.createProduct(productPrice.getProduct());
		productPrice.setProduct(prod);
		return productPriceRepository.save(productPrice);
	}

	@Override
	public ProductPrice readById(long id) {
		return productPriceRepository.readByProductpriceid(id);
	}

	@Override
	public ProductPrice getActiveByProduct(Product product) {
		List<ProductPrice> result = productPriceRepository.findByProductAndStatus(product, RecordStatus.Active);
		if (null != result && !result.isEmpty())
			return result.get(0);
		return null;
	}

	@Override
	public List<ProductPrice> getAllInactiveByProduct(Product product) {
		return productPriceRepository.findByProductAndStatus(product, RecordStatus.InActive);
	}

	@Override
	public List<ProductPrice> getByProduct(Product product) {
		return productPriceRepository.findByProduct(product);
	}

	@Override
	public ProductPrice update(ProductPrice productPrice) {

		if (null != readById(productPrice.getProductpriceid()))
			return productPriceRepository.save(productPrice);

		transactionInfo.generateException("UPDATE_FAILED", NotificationInfo.ERROR,
				Status.INTERNAL_SERVER_ERROR.getStatusCode());
		return null;
	}

}
