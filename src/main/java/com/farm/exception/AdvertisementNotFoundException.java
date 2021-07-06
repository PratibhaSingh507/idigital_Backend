
package com.farm.exception;


/**
 * This is FarmerNotFoundException class
 * @author Manoj Chuadhary
 * 
 */

public class AdvertisementNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AdvertisementNotFoundException() {
	}

	public AdvertisementNotFoundException(String message) {
		super(message);
	}

}

