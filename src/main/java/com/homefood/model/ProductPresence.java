package com.homefood.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.homefood.codetype.DayAvailablity;
import com.homefood.codetype.RecordStatus;
import com.homefood.core.LocalDateTimeConverter;

@Entity(name = "productpresence")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductPresence {

	@Id
	@GeneratedValue
	private long prodsectionid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	@JsonIgnore
	private Product product;

	@Column(name = "starttime")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime startTime;

	@Column(name = "endtime")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime endTime;

	@Transient
	private boolean isPresent;

	@Column(name = "availableday")
	@Enumerated(EnumType.STRING)
	private DayAvailablity availablity = DayAvailablity.Everyday;

	@Column(name = "outofstock")
	private Boolean outofStock = false;

	@Column(name = "quantity")
	private int quantity = 20;

	@NotNull
	@CreatedDate
	@Column(name = "createddate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdDate;

	@NotNull
	@LastModifiedDate
	@Column(name = "lastmodifieddate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime lastModifiedDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "recordstatus")
	private RecordStatus recordStatus = RecordStatus.Active;

	public long getProdsectionid() {
		return prodsectionid;
	}

	public void setProdsectionid(long prodsectionid) {
		this.prodsectionid = prodsectionid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public boolean isPresent() {
		return (getStartTime().isBefore(LocalDateTime.now()) && getEndTime().isAfter(LocalDateTime.now())) ? true
				: false;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public DayAvailablity getAvailablity() {
		return availablity;
	}

	public void setAvailablity(DayAvailablity availablity) {
		this.availablity = availablity;
	}

	public Boolean getOutofStock() {
		return outofStock;
	}

	public void setOutofStock(Boolean outofStock) {
		this.outofStock = outofStock;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
