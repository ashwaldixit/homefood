package com.homefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Caterer;
import com.homefood.model.CatererLocation;
import com.homefood.model.Location;
import com.homefood.repository.CatererLocationRepository;

@Service
@Transactional
public class CatererLocationServiceImpl implements CatererLocationService {

	@Autowired
	private CatererLocationRepository repository;

	@Override
	public CatererLocation findByid(long id) {
		return repository.findByCatererlocationid(id);
	}

	@Override
	public List<CatererLocation> readAllActiveByCaterer(Caterer caterer) {
		return repository.findByCatererAndStatus(caterer, RecordStatus.Active);
	}

	@Override
	public List<CatererLocation> readAllActiveByLocation(Location location) {
		return repository.findByLocationAndStatus(location, RecordStatus.Active);
	}

}
