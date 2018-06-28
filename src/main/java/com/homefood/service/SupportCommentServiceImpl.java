package com.homefood.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.SupportComment;
import com.homefood.model.SupportTicket;
import com.homefood.model.User;
import com.homefood.repository.SupportCommentRepository;

@Service
@Transactional
public class SupportCommentServiceImpl implements SupportCommentService {

	@Autowired
	SupportCommentRepository supportCommentRepository;

	@Override
	public SupportComment update(SupportComment supportComment) {
		return save(supportComment);
	}

	@Override
	public SupportComment save(SupportComment supportComment) {
		return supportCommentRepository.save(supportComment);
	}

	@Override
	public SupportComment findById(long id) {
		return supportCommentRepository.findBySupportticketcommentid(id);
	}

	@Override
	public List<SupportComment> findByUserAndStatus(User user, RecordStatus status) {
		return supportCommentRepository.findByUserAndRecordStatus(user, status);
	}

	@Override
	public List<SupportComment> findBySupportTicketOrderByCreatedDateAsc(SupportTicket supportTicket) {
		return supportCommentRepository.findBySupportTicketOrderByCreatedDateAsc(supportTicket);
	}

}
