package com.homefood.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Location;
import com.homefood.repository.LocationRepository;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository repository;

	@Autowired
	CSVUtilReaderService csvUtilReaderService;

	@Override
	public Location readByLocationid(long id) {
		return repository.findByLocationid(id);
	}

	@Override
	public Location readByDistrictAndCityAndArea(String district, String City, String area) {
		return repository.findByDistrictAndCityAndAreaAndStatus(district, City, area, RecordStatus.Active);
	}

	@Override
	public List<Location> readByDistrict(String district) {
		return repository.findByDistrictAndStatus(district, RecordStatus.Active);
	}

	@Override
	public List<Location> readByCity(String city) {
		return repository.findByCityAndStatus(city, RecordStatus.Active);
	}

	@Override
	public List<Location> readByState(String state) {
		return repository.findByStateAndStatus(state, RecordStatus.Active);
	}

	@Override
	public Location createLocation(Location location) {
		return repository.save(location);
	}

	@Override
	public void validate(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public Location validateAndCreate(Location location) {
		validate(location);
		return createLocation(location);
	}

	@Override
	public void loadCitiesFromExcel() {
		try {
			csvUtilReaderService.readLocationsFromExcel().stream().forEach(item -> validateAndCreate(item));
		} catch (IllegalStateException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
