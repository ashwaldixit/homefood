package com.homefood.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Product;
import com.homefood.model.ProductOrder;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Serializable> {

	public ProductOrder findByOrderid(long id);

	public List<ProductOrder> findByProductAndStatus(Product product, RecordStatus recordStatus);

	public List<ProductOrder> findByProductAndDeliverydate(Product product, LocalDateTime deliveryDate);

	public List<ProductOrder> findByDeliverydate(LocalDateTime deliveryDate);

	public List<ProductOrder> findByDeliverydateAndOrderStatus(LocalDateTime deliveryDate, OrderStatus orderStatus);

	public List<ProductOrder> findByProductAndDeliverydateAndOrderStatus(Product product, LocalDateTime deliveryDate,
			OrderStatus orderStatus);
}
