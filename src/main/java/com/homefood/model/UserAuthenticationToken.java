package com.homefood.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.homefood.codetype.RecordStatus;

@Entity(name = "userauthenticationtoken")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserAuthenticationToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@JsonIgnore
	private long id;

	@Column(unique = true)
	private String token = "";

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", unique = true, referencedColumnName = "userid")
	private User user;

	@Column
	@JsonIgnore
	private LocalDateTime expiryDate = LocalDateTime.now();

	@NotNull
	@CreatedDate
	@JsonIgnore
	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@NotNull
	@LastModifiedDate
	@JsonIgnore
	@Column(name = "lastmodifieddate")
	private LocalDateTime lastModifiedDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "recordstatus")
	@JsonIgnore
	private RecordStatus status = RecordStatus.Active;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
