package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.Priority;
import com.homefood.codetype.SupportTicketStatus;
import com.homefood.model.SupportTicket;
import com.homefood.model.User;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Serializable> {

	public SupportTicket findBysupportticketid(long supportticketid);

	public List<SupportTicket> findByUserAndTicketStatus(User user, SupportTicketStatus status);

	public List<SupportTicket> findByUser(User user);

	public List<SupportTicket> findByUserAndPriority(User user, Priority priority);

	public List<SupportTicket> findByTitle(String title);

}
