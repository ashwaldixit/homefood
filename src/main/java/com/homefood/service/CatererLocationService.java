package com.homefood.service;

import java.util.List;

import com.homefood.model.Caterer;
import com.homefood.model.CatererLocation;
import com.homefood.model.Location;

public interface CatererLocationService {
	
	public CatererLocation findByid(long id);

	public List<CatererLocation> readAllActiveByCaterer(Caterer caterer);

	public List<CatererLocation> readAllActiveByLocation(Location location);

}
