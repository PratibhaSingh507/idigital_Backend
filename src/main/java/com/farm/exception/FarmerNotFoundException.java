package com.farm.exception;


/**
 * This is FarmerNotFoundException class
 * @author Manoj Chuadhary
 * 
 */

public class FarmerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public FarmerNotFoundException() {
	}

	public FarmerNotFoundException(String message) {
		super(message);
	}

}
