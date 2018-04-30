package com.homefood.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.model.ApplicationData;

@Repository
public interface ApplicationDataRepository extends JpaRepository<ApplicationData, Serializable> {

	public ApplicationData findByApplicationdataid(long applicationDataID);

	public ApplicationData findByObsKey(String key);

}
