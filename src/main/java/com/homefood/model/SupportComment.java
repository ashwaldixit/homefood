package com.homefood.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homefood.codetype.RecordStatus;

@Entity(name = "supportticketcomment")
public class SupportComment {

	@Id
	@GeneratedValue
	long supportticketcommentid;

	@Column
	private String comment;

	@Column
	private RecordStatus recordStatus = RecordStatus.Active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "supportticketid", name = "supportticket", nullable = false)
	@JsonIgnore
	SupportTicket supportTicket;

	@Column(name = "createddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private User user;

	public long getSupportticketcommentid() {
		return supportticketcommentid;
	}

	public void setSupportticketcommentid(long supportticketcommentid) {
		this.supportticketcommentid = supportticketcommentid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
	}

	public SupportTicket getSupportTicket() {
		return supportTicket;
	}

	public void setSupportTicket(SupportTicket supportTicket) {
		this.supportTicket = supportTicket;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
