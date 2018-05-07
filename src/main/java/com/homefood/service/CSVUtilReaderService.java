package com.homefood.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.homefood.model.Location;
import com.homefood.model.util.CitiesReport;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CSVUtilReaderService {

	public List<Location> readLocationsFromExcel() throws IllegalStateException, FileNotFoundException {
		List<Location> locations = new ArrayList<Location>();
		List<CitiesReport> beans = new CsvToBeanBuilder<CitiesReport>(
				new FileReader(Thread.currentThread().getContextClassLoader().getResource("cities/cities.csv").getPath()))
						.withType(CitiesReport.class).withSeparator(',').withOrderedResults(true).build().parse();

		beans.stream().forEach(item -> {
			Location location = new Location();
			location.setArea(item.getCity());
			location.setCountry("India");
			location.setDistrict("Bengaluru");
			location.setState("Karnataka");
			location.setCity("Bengaluru");
			location.setZipCode(item.getZipCode());
			locations.add(location);
		});
		return locations;
	}

}
