package com.homefood.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CustomerOrder {
	
	

}
