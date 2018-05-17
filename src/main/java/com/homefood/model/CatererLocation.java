package com.homefood.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.homefood.codetype.RecordStatus;

@Entity(name = "catererlocations")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CatererLocation {

	@Id
	@GeneratedValue
	private long catererlocationid;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Caterer caterer;

	@ManyToOne(cascade = CascadeType.ALL)
	private Location location;

	@NotNull
	@CreatedDate
	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@NotNull
	@LastModifiedDate
	@Column(name = "lastmodifieddate")
	private LocalDateTime lastModifiedDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "recordstatus")
	private RecordStatus status = RecordStatus.Active;

	
	public long getCatererlocationid() {
		return catererlocationid;
	}

	public void setCatererlocationid(long catererlocationid) {
		this.catererlocationid = catererlocationid;
	}

	public Caterer getCaterer() {
		return caterer;
	}

	public void setCaterer(Caterer caterer) {
		this.caterer = caterer;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

}
