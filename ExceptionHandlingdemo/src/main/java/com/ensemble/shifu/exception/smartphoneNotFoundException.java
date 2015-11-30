package com.ensemble.shifu.exception;

public class smartphoneNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724403L;
	private final int smartphoneId;
	
	public smartphoneNotFoundException(int id) {
		smartphoneId = id;
	}
	
	public int getSmartphoneId() {
		return smartphoneId;
	}}