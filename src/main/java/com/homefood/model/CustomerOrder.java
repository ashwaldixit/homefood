package com.homefood.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.homefood.codetype.OrderStatus;
import com.homefood.codetype.RecordStatus;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CustomerOrder {

	@Id
	@GeneratedValue
	private long customerorderid;

	@NotNull
	@CreatedDate
	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@NotNull
	@LastModifiedDate
	@Column(name = "lastmodifieddate")
	private LocalDateTime lastModifiedDate;

	@Enumerated(EnumType.STRING)
	private RecordStatus status = RecordStatus.Active;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customerOrder")
    @JsonIgnore
	private List<ProductOrder> productOrders;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid", nullable = false, updatable = false)
//	@JsonManagedReference
	private User customer;

	@Enumerated(EnumType.STRING)
	@Column(name = "orderstatus")
	private OrderStatus orderStatus = OrderStatus.Open;
	
	

	public long getCustomerorderid() {
		return customerorderid;
	}

	public void setCustomerorderid(long customerorderid) {
		this.customerorderid = customerorderid;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public RecordStatus getStatus() {
		return status;
	}

	public void setStatus(RecordStatus status) {
		this.status = status;
	}

	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
