package com.homefood.service;

import java.util.List;

import com.homefood.model.Category;

public interface CategoryService {

	public Category createCategory(Category category);

	public Category readById(long Id);

	public Category readActiveByName(String name);

	public List<Category> readAllInActiveByName(String name);

	public void validate(Category category);

	public Category validateAndCreate(Category category);

	public Category update(Category category);

}
