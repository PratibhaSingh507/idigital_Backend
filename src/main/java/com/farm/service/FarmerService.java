package com.farm.service;

import java.util.List;

import com.farm.model.Advertisement;
import com.farm.model.Farmer;

/**
 * This is Farmer Service interface
 * @author Manoj Chuadhary
 * 
 */

public interface FarmerService {
	
	

	boolean loginFarmer(String emailId, String password);

	public List<Farmer> viewFarmer();

	public Farmer getFarmerById(int farmId);

	public Farmer registerFarmer(Farmer farmer);

	public Farmer updateFarmer(Farmer farmer);

	public String deleteFarmer(int farmId);

	public List<Advertisement> viewAdvertisement();
	
	

}
