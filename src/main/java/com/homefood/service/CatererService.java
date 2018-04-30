package com.homefood.service;

import java.util.List;

import com.homefood.model.Caterer;

public interface CatererService {

	public Caterer createCaterer(Caterer caterer);

	public Caterer readById(long id);

	public void validate(Caterer caterer);

	public Caterer validateAndCreate(Caterer caterer);

	public Caterer readActiveByName(String name);

	public List<Caterer> readAllInActiveByName(String name);

	public Caterer update(Caterer caterer);
}
