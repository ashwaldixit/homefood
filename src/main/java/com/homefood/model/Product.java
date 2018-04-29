package com.homefood.model;

import java.time.LocalDateTime;
import java.util.List;

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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.homefood.codetype.RecordStatus;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	@GeneratedValue
	private long productid;

	@Column(unique = true, nullable = false)
	private String name;

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

	@Column(name = "imageurl")
	private String imageUrl;

	@Column(nullable = false)
	private String description;

	@Version
	private int version;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryid", nullable = false)
	@JsonBackReference
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catererid", nullable = false)
	@JsonBackReference
	private Caterer caterer;

	@Column
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@JsonManagedReference
	private List<ProductPresence> presence;

	public RecordStatus getStatus() {
		return status;
	}

	public void setStatus(RecordStatus status) {
		this.status = status;
	}

	public Caterer getCaterer() {
		return caterer;
	}

	public void setCaterer(Caterer caterer) {
		this.caterer = caterer;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ProductPresence> getPresence() {
		return presence;
	}

	public void setPresence(List<ProductPresence> presence) {
		this.presence = presence;
	}

}
