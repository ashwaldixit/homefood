package com.homefood.service;

import java.util.List;

import com.homefood.model.Location;

public interface LocationService {
	
	public Location readByLocationid(long id);

	public Location readByDistrictAndCityAndArea(String district, String City, String area);

	public List<Location> readByDistrict(String district);

	public List<Location> readByCity(String city);

	public List<Location> readByState(String state);


}
