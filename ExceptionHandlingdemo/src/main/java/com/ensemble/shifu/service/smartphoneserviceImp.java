package com.ensemble.shifu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensemble.shifu.exception.smartphoneNotFoundException;
import com.ensemble.shifu.model.smartphone;
import com.ensemble.shifu.repository.smartphonerepository;



@Service
@Transactional(rollbackFor = { smartphoneNotFoundException.class })
public class smartphoneserviceImp implements smartphoneservice {
	@Autowired
	private smartphonerepository smartphoneRepository;
	
	@Override
	public smartphone create(smartphone sp) {
		
		return smartphoneRepository.save(sp);
	}

	@Override
	public smartphone get(Integer id) {
		smartphone sp = null;
		if (id instanceof Integer)
			sp = smartphoneRepository.findOne(id);
		if (sp != null)
			return sp;
		throw new smartphoneNotFoundException(id);
	}
	

	@Override
	public List<smartphone> getAll() {
		return smartphoneRepository.findAll();
	}

	@Override
	public smartphone update(smartphone sp) {
		smartphone sPhoneToUpdate = get(sp.getId());
		sPhoneToUpdate.update(sp);
		return sPhoneToUpdate;
	}

	@Override
	public smartphone delete(Integer id) {
		smartphone sPhone = get(id);
		smartphoneRepository.delete(id);
		return sPhone;
	}

}
