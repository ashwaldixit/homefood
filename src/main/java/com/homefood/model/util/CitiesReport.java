package com.homefood.model.util;

import com.opencsv.bean.CsvBindByName;

public class CitiesReport {

	@CsvBindByName(column = "Cities")
	private String city;

	@CsvBindByName(column = "Zipcodes")
	private String zipCode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	

}