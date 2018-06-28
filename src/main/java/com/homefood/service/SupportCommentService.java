package com.homefood.service;

import java.util.List;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.SupportComment;
import com.homefood.model.SupportTicket;
import com.homefood.model.User;

public interface SupportCommentService {

	public SupportComment update(SupportComment supportComment);

	public SupportComment save(SupportComment supportComment);

	public SupportComment findById(long id);

	public List<SupportComment> findByUserAndStatus(User user, RecordStatus status);

	public List<SupportComment> findBySupportTicketOrderByCreatedDateAsc(SupportTicket supportTicket);

}
