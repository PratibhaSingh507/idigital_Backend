package com.farm.exception;

public class CropNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CropNotFoundException() {
	}

	public CropNotFoundException(String message) {
		super(message);
	}
	
}
