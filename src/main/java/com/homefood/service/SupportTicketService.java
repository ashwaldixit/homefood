package com.homefood.service;

import java.util.List;

import com.homefood.codetype.Priority;
import com.homefood.codetype.SupportTicketStatus;
import com.homefood.model.SupportTicket;
import com.homefood.model.User;

public interface SupportTicketService {

	public SupportTicket findById(long id);

	public List<SupportTicket> findByUserAndStatus(User user, SupportTicketStatus status);

	public List<SupportTicket> findByUser(User user);

	public List<SupportTicket> findByUserAndPriority(User user, Priority priority);

	public List<SupportTicket> findByTitle(String title);

	public SupportTicket update(SupportTicket supportTicket);

	public SupportTicket save(SupportTicket supportTicket);

	public SupportTicket close(long ticketID);

	public SupportTicket reopen(long ticketID);

	public SupportTicket resolve(long ticketID);
}
