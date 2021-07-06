package com.farm.controller;

import java.util.List;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farm.model.Advertisement;
import com.farm.model.Farmer;
import com.farm.model.MessageEntity;
import com.farm.exception.FarmerNotFoundException;
import com.farm.service.FarmerService;


/**
 * This is Farmer Controller class
 * @author Manoj Chaudhary
 * 
 */

@RestController
@RequestMapping("/farmer")
@CrossOrigin(origins = "*")
public class FarmerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FarmerController.class);

	@Autowired
	private FarmerService farmerService;
	
	
	
	@PostMapping(value = "/loginFarmer")
	public ResponseEntity<String> loginFarmer(@RequestBody Farmer login) throws FarmerNotFoundException{
		//LOGGER.log(Level.INFO, "Login:");
		if (farmerService.loginFarmer(login.getEmailId(), login.getPassword()))
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Login Failed)", HttpStatus.BAD_REQUEST);
	}
	
	


	@PostMapping("/registerFarmer")
	public ResponseEntity<Farmer> registerFarmer(@RequestBody Farmer farmer) {

		LOGGER.trace("Entering into method registerFarmer");

		Farmer farmer2 = farmerService.registerFarmer(farmer);
		if (farmer2 != null) {
			LOGGER.info("Farmer Registered");
			return new ResponseEntity<Farmer>(farmer2, HttpStatus.OK);
		}
		LOGGER.error("Error 404");
		return new ResponseEntity("farmer registration failed...", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/updateFarmer")
	public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer) throws FarmerNotFoundException {

		LOGGER.trace("Entering into method registerFarmer");

		Farmer farmer2 = farmerService.updateFarmer(farmer);
		if (farmer2 != null) {
			LOGGER.info("Farmer updated");
			return new ResponseEntity<Farmer>(farmer2, HttpStatus.OK);
		}
		LOGGER.error("Error 404");
		return new ResponseEntity("Tutor update failed...", HttpStatus.NOT_FOUND);

	}

	@GetMapping("/viewFarmer")
	public List<Farmer> viewFarmer() {

		LOGGER.info("Farmer searching all Farmers");
		return farmerService.viewFarmer();
	}

	
	  @GetMapping("/viewFarmer/{farmId}") 
	  public Farmer getFarmerById(@PathVariable int farmId) throws FarmerNotFoundException 
	  {
	  
	  LOGGER.info("farmer get by Id"); 
	  return farmerService.getFarmerById(farmId);
	  }
	 
	
	
	@DeleteMapping("/deleteFarmer/{farmId}")
	public String deleteFarmer(@PathVariable int farmId) throws FarmerNotFoundException {

		LOGGER.info("Farmer deleted");
		return "Deleted Farmer: " + farmerService.deleteFarmer(farmId);
	}

	@GetMapping("/viewAdvertisement")
	public List<Advertisement> viewAdvertisement() {

		LOGGER.info("Farmer searching all Advertisement");
		return farmerService.viewAdvertisement();
	}

}




  