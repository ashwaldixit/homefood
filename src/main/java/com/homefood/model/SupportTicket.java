package com.homefood.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.homefood.codetype.Priority;
import com.homefood.codetype.RecordStatus;
import com.homefood.codetype.SupportTicketStatus;


@Entity(name = "supportticket")
public class SupportTicket {

	@Id
	@GeneratedValue
	private long supportticketid;

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private Priority priority = Priority.Medium;

	@Column(name = "createddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();

	@Column
	@Enumerated(EnumType.STRING)
	private RecordStatus status = RecordStatus.Active;

	@Column
	private SupportTicketStatus ticketStatus = SupportTicketStatus.OPEN;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private User user;

	@Column
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supportTicket")
	private List<SupportComment> comments;

	public long getSupportticketid() {
		return supportticketid;
	}

	public void setSupportticketid(long supportticketid) {
		this.supportticketid = supportticketid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public RecordStatus getStatus() {
		return status;
	}

	public void setStatus(RecordStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SupportComment> getComments() {
		return comments;
	}

	public void setComments(List<SupportComment> comments) {
		this.comments = comments;
	}

	public SupportTicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(SupportTicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

}
