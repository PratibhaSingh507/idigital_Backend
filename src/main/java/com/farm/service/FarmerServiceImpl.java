package com.farm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.farm.controller.FarmerController;
import com.farm.model.Advertisement;
import com.farm.model.Farmer;

import com.farm.dao.AdvertisementJpaDao;
import com.farm.dao.FarmerJpaDao;

/**
 * This is Farmer Service implementation class
 * @author Manoj Chuadhary
 * 
 */

@Service
public class FarmerServiceImpl implements FarmerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FarmerController.class);

	@Autowired
	private FarmerJpaDao farmerRepo;

	@Autowired
	private AdvertisementJpaDao adRepo;

	@Override
	public List<Farmer> viewFarmer() {

		LOGGER.info("Farmer getting all farmers service");
		return farmerRepo.findAll();
	}

	@Override
	public Farmer registerFarmer(Farmer farmer) {

		LOGGER.info("Farmer Registere service");
		return farmerRepo.save(farmer);
	}

	@Override
	public Farmer updateFarmer(Farmer farmer) {

		LOGGER.info("Farmer update profile service");
		Farmer farmer2 = farmerRepo.findById(farmer.getFarmerId()).orElse(null);
		farmer2.setFarmerId(farmer.getFarmerId());
		farmer2.setFarmerName(farmer.getFarmerName());
		farmer2.setEmailId(farmer.getEmailId());
		farmer2.setPassword(farmer.getPassword());
		farmer2.setCropName(farmer.getCropName());
		farmer2.setLocation(farmer.getLocation());
		farmer2.setContactNo(farmer.getContactNo());
		return farmerRepo.save(farmer2);

	}

	@Override
	public String deleteFarmer(int farmId) {

		LOGGER.info("Farmer delete service");
		Farmer farmer = farmerRepo.findById(farmId).get();
		farmerRepo.deleteById(farmId);
		return farmer.toString();

	}

	@Override
	public Farmer getFarmerById(int farmId) {

		LOGGER.info("Farmer get by id Service");
		return farmerRepo.findById(farmId).orElse(new Farmer(0, "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"));

	}

	@Override
	public List<Advertisement> viewAdvertisement() {

		LOGGER.info("Farmer getting all Advertisement");
		return adRepo.findAll();
	}

	
	  @Override 
	  
	  public boolean loginFarmer(String emailId, String password) {
			// TODO Auto-generated method stub
			Boolean found = false;
			if (emailId == null) {
				found = false;
			}
			List<Farmer> farmer = farmerRepo.findAll();
			for (Farmer farmers : farmer) {
				if ((farmers.getEmailId().equals(emailId)) && (farmers.getPassword().equals(password))) {
					found = true;
					break;
				}
			}
			return found;
		}
	 
}
