package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Product;
import com.homefood.model.ProductPrice;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Serializable> {

	public ProductPrice readByProductpriceid(long id);

	public List<ProductPrice> findByProduct(Product product);

	public List<ProductPrice> findByProductAndStatus(Product product, RecordStatus status);

}
