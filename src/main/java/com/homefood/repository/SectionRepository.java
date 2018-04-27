package com.homefood.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.homefood.model.Section;

@Repository
public interface SectionRepository extends CrudRepository<Section, Serializable> {

}
