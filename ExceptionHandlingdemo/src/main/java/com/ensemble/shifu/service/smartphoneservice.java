package com.ensemble.shifu.service;

import java.util.List;

import com.ensemble.shifu.model.smartphone;


public interface smartphoneservice {
	public smartphone create(smartphone sp);
	public smartphone get(Integer id); //throws SmartphoneNotFoundException;
	public List<smartphone> getAll();
	public smartphone update(smartphone sp);
	public smartphone delete(Integer id);
}
