package com.homefood.service;

import java.util.List;
import java.util.Set;

import com.homefood.model.Category;
import com.homefood.model.Caterer;
import com.homefood.model.Location;
import com.homefood.model.User;

public interface CatererService {

	public Caterer createCaterer(Caterer caterer);

	public Caterer readById(long id);

	public void validate(Caterer caterer);

	public Caterer validateAndCreate(Caterer caterer);

	public Caterer readActiveByName(String name);

	public List<Caterer> readAllInActiveByName(String name);

	public Caterer update(Caterer caterer);

	public List<Caterer> readAllInActiveCaterers();

	public Caterer getByUser(User user);

	public List<Location> getAllActiveLocations(Caterer caterer);
	
	public Set<Category> getAllActiveCategories(Caterer caterer);
}
