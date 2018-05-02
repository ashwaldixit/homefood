package com.homefood.service;

import java.time.LocalDateTime;
import java.util.List;

import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;
import com.homefood.model.Customer;
import com.homefood.model.ProductOrder;
import com.homefood.model.Product;

public interface ProductOrderService {

	public ProductOrder createOrder(ProductOrder order);

	public ProductOrder readById(long id);

	public void validate(ProductOrder order);

	public ProductOrder validateAndCreate(ProductOrder order);

	public ProductOrder update(ProductOrder order);

	public List<ProductOrder> readByCustomerAndStatus(Customer customer, OrderStatus orderStatus);

	public List<ProductOrder> readAllOpenOrdersOfCustomer(Customer customer);

	public List<ProductOrder> readAllConfirmedOrdersOfCustomer(Customer customer);

	public List<ProductOrder> readAllDeliveredOrdersOfCustomer(Customer customer);

	public List<ProductOrder> readAllCancelledOrdersOfCustomer(Customer customer);

	public List<ProductOrder> readAllByCustomerAndProduct(Customer customer, Product product);

	public List<ProductOrder> readdAllByProductAndStatus(Product product, RecordStatus recordStatus);

	public List<ProductOrder> readAllActiveByProduct(Product product);

	public List<ProductOrder> readAllInActiveByProduct(Product product);

	public List<ProductOrder> readAllByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

	public List<ProductOrder> readAllByDeliveryDate(LocalDateTime deliveryDate);

	public List<ProductOrder> readAllConfirmedByDeliveryDate(LocalDateTime deliveryDate);

	public List<ProductOrder> readAllDeliveredByDeliveryDate(LocalDateTime deliveryDate);

	public List<ProductOrder> readAllOpenByDeliveryDate(LocalDateTime deliveryDate);

	public List<ProductOrder> readAllCancelledByDeliveryDate(LocalDateTime deliveryDate);

	public List<ProductOrder> readAllByProductAndDeliveryDateAndOrderStatus(Product product, LocalDateTime deliveryDate,
			OrderStatus orderStatus);

	public List<ProductOrder> readAllConfirmedByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

	public List<ProductOrder> readAllDeliveredByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

	public List<ProductOrder> readAllCancelledByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

	public List<ProductOrder> readAllOpenByProductAndDeliveryDate(Product product, LocalDateTime deliveryDate);

}
