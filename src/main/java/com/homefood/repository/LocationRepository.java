package com.homefood.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Serializable> {

	public Location findByLocationid(long id);

	public Location findByDistrictAndCityAndAreaAndStatus(String district, String City, String area,
			RecordStatus recordStatus);

	public List<Location> findByDistrictAndStatus(String district, RecordStatus recordStatus);

	public List<Location> findByCityAndStatus(String city, RecordStatus recordStatus);

	public List<Location> findByStateAndStatus(String state, RecordStatus recordStatus);

}
