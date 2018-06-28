package com.homefood.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homefood.codetype.Priority;
import com.homefood.codetype.SupportTicketStatus;
import com.homefood.model.SupportTicket;
import com.homefood.model.User;
import com.homefood.repository.SupportTicketRepository;

@Service
@Transactional
public class SupportTicketServiceImpl implements SupportTicketService {

	@Autowired
	SupportTicketRepository supportTicketRepository;

	@Override
	public SupportTicket findById(long id) {
		return supportTicketRepository.findBysupportticketid(id);
	}

	@Override
	public List<SupportTicket> findByUserAndStatus(User user, SupportTicketStatus status) {
		return supportTicketRepository.findByUserAndTicketStatus(user, status);
	}

	@Override
	public List<SupportTicket> findByUser(User user) {
		return supportTicketRepository.findByUser(user);
	}

	@Override
	public List<SupportTicket> findByUserAndPriority(User user, Priority priority) {
		return supportTicketRepository.findByUserAndPriority(user, priority);
	}

	@Override
	public List<SupportTicket> findByTitle(String title) {
		return supportTicketRepository.findByTitle(title);
	}

	@Override
	public SupportTicket update(SupportTicket supportTicket) {
		return save(supportTicket);
	}

	@Override
	public SupportTicket save(SupportTicket supportTicket) {
		return supportTicketRepository.save(supportTicket);
	}

	@Override
	public SupportTicket close(long ticketID) {
		SupportTicket ticket = findById(ticketID);
		ticket.setTicketStatus(SupportTicketStatus.CLOSED);
		return save(ticket);
	}

	@Override
	public SupportTicket reopen(long ticketID) {
		SupportTicket ticket = findById(ticketID);
		ticket.setTicketStatus(SupportTicketStatus.REOPENED);
		return save(ticket);
	}

	@Override
	public SupportTicket resolve(long ticketID) {
		SupportTicket ticket = findById(ticketID);
		ticket.setTicketStatus(SupportTicketStatus.RESOLVED);
		return save(ticket);
	}

}
