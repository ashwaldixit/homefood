package com.homefood.model;

import java.time.LocalDateTime;

import com.homefood.codetype.DayAvailablity;

public class ProductCreate {

	private String name;
	private String imageUrl;
	private Category category;
	private String Description;
	private Caterer caterer;
	private double price;
	private DayAvailablity dayAvailability;
	private LocalDateTime day;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Caterer getCaterer() {
		return caterer;
	}

	public void setCaterer(Caterer caterer) {
		this.caterer = caterer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public DayAvailablity getDayAvailability() {
		return dayAvailability;
	}

	public void setDayAvailability(DayAvailablity dayAvailability) {
		this.dayAvailability = dayAvailability;
	}

	public LocalDateTime getDay() {
		return day;
	}

	public void setDay(LocalDateTime day) {
		this.day = day;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
