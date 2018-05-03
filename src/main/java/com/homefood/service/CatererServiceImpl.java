package com.homefood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homefood.codetype.NotificationInfo;
import com.homefood.codetype.RecordStatus;
import com.homefood.core.TransactionInfo;
import com.homefood.model.Caterer;
import com.homefood.repository.CatererRepository;

@Service
@Transactional
public class CatererServiceImpl implements CatererService {

	@Autowired
	CatererRepository catererRepository;

	@Autowired
	TransactionInfo transactionInfo;

	@Override
	public Caterer createCaterer(Caterer caterer) {
		return catererRepository.save(caterer);
	}

	@Override
	public Caterer readById(long id) {
		return catererRepository.findByCatererid(id);
	}

	@Override
	public void validate(Caterer caterer) {
		List<Object> args = new ArrayList<Object>();
		args.add(caterer.getClass().getSimpleName() + " name");
		args.add(caterer.getName());
		List<Caterer> caterers = catererRepository.findByNameAndRecordStatus(caterer.getName(), RecordStatus.Active);
		if (caterer.getName() == null || caterer.getName().isEmpty()) {
			transactionInfo.generateException("EMPTY_FILED", args, NotificationInfo.ERROR, 501);
		} else if (!caterers.isEmpty() || null == caterers) {
			transactionInfo.generateException("ALREADY_EXISTS", args, NotificationInfo.ERROR, 501);
		}
	}

	@Override
	public Caterer validateAndCreate(Caterer caterer) {
		validate(caterer);
		return createCaterer(caterer);
	}

	@Override
	public Caterer readActiveByName(String name) {

		List<Caterer> result = catererRepository.findByNameAndRecordStatus(name, RecordStatus.Active);
		if (null != result && !result.isEmpty())
			return result.get(0);
		return null;
	}

	@Override
	public List<Caterer> readAllInActiveByName(String name) {
		return catererRepository.findByNameAndRecordStatus(name, RecordStatus.Active);
	}

	@Override
	public Caterer update(Caterer caterer) {
		return createCaterer(caterer);
	}

	@Override
	public List<Caterer> readAllInActiveCaterers() {
		return catererRepository.findByRecordStatus(RecordStatus.Active);
	}

}
