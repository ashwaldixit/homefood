package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Caterer;
import com.homefood.model.CatererLocation;
import com.homefood.model.Location;

@Repository
public interface CatererLocationRepository extends JpaRepository<CatererLocation, Serializable> {

	public CatererLocation findByCatererlocationid(long id);

	public List<CatererLocation> findByCatererAndStatus(Caterer caterer, RecordStatus status);

	public List<CatererLocation> findByLocationAndStatus(Location location, RecordStatus status);

}
