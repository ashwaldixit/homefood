package com.homefood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.NotificationInfo;
import com.homefood.codetype.RecordStatus;
import com.homefood.core.TransactionInfo;
import com.homefood.model.Category;
import com.homefood.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	TransactionInfo transactionInfo;

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
		List<Object> args = new ArrayList<Object>();
		args.add(category.getClass().getSimpleName() + " name");
		args.add(category.getName());
		List<Category> categories = categoryRepository.findByNameAndRecordStatus(category.getName(),
				RecordStatus.Active);
		if (category.getName() == null || category.getName().isEmpty()) {
			transactionInfo.generateException("EMPTY_FILED", args, NotificationInfo.ERROR, 501);
		} else if (categories == null || !categories.isEmpty()) {
			transactionInfo.generateException("ALREADY_EXISTS", args, NotificationInfo.ERROR, 501);
		}
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

	@Override
	public List<Category> getAllActiveCategories() {
		return categoryRepository.findByRecordStatus(RecordStatus.Active);
	}

}
