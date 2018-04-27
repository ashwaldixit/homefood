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
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.homefood.codetype.RecordStatus;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Caterer {

	@Id
	@GeneratedValue
	private long catererid;

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
	private RecordStatus recordStatus = RecordStatus.Active;

	@Column(name = "imageurl")
	private String imageUrl;

	@Column(nullable = false)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CategoryProduct> categoryProducts;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Section> sections;

	@Version
	private int version;

	public List<CategoryProduct> getCategoryProducts() {
		return categoryProducts;
	}

	public void setCategoryProducts(List<CategoryProduct> categoryProducts) {
		this.categoryProducts = categoryProducts;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public long getCatererid() {
		return catererid;
	}

	public void setCatererid(long catererid) {
		this.catererid = catererid;
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

	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
