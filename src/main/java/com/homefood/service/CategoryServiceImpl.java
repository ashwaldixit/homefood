package com.homefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.RecordStatus;
import com.homefood.model.Category;
import com.homefood.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category readById(long id) {
		return categoryRepository.findByCategoryid(id);
	}

	@Override
	public Category readActiveByName(String name) {
		List<Category> result = categoryRepository.findByNameAndRecordStatus(name, RecordStatus.Active);
		if (null != result && !result.isEmpty())
			return result.get(0);
		return null;
	}

	@Override
	public List<Category> readAllInActiveByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByNameAndRecordStatus(name, RecordStatus.InActive);
	}

	@Override
	public void validate(Category category) {
		// TODO Auto-generated method stub

	}

	@Override
	public Category validateAndCreate(Category category) {
		validate(category);
		return createCategory(category);
	}

	@Override
	public Category update(Category category) {
		return createCategory(category);
	}

}
