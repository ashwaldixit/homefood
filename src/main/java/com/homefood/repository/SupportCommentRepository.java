package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.SupportComment;
import com.homefood.model.SupportTicket;
import com.homefood.model.User;

@Repository
public interface SupportCommentRepository extends JpaRepository<SupportComment, Serializable> {

	public SupportComment findBySupportticketcommentid(long id);

	public List<SupportComment> findBySupportTicketOrderByCreatedDateAsc(SupportTicket supportTicket);

	public List<SupportComment> findByUserAndRecordStatus(User user, RecordStatus status);
}
