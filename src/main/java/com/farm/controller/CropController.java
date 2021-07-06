package com.farm.controller;


import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farm.model.CropEntity;
//import com.farm.exception.AdminException;
import com.farm.exception.CropNotFoundException;
//import com.farm.model.Admin;
import com.farm.service.CropService;

@RestController
@RequestMapping("/crop")
@CrossOrigin(origins = "*")

public class CropController {
	
	private static final Logger logger = LoggerFactory.getLogger(CropController.class);
	@Autowired
	private CropService cropService;
	
	@PostMapping("/sellCrop")
	public ResponseEntity<CropEntity>sellCrop(@RequestBody CropEntity crop) {
		logger.trace("Entering into method sellcrop");
		
		CropEntity crop2=cropService.sellCrop(crop);
		if(crop2!=null) {
			logger.info("Crop sold successfully");
			return new ResponseEntity<CropEntity>(crop2, HttpStatus.OK);
		}
		logger.error("Error 404");
		return new ResponseEntity("Failed Could not able to sell the crop...",HttpStatus.NOT_FOUND);
	 }
	
	

	
	 @PutMapping("/updateCrop")
	 public ResponseEntity<CropEntity>updateCrop(@RequestBody CropEntity crop) throws CropNotFoundException{
			logger.trace("Entering into method updateCrop");
			
			CropEntity crop2=cropService.updateCrop(crop);
			if(crop2!=null) {
				logger.info("Crop details uploaded successfully");
				return new ResponseEntity<CropEntity>(crop2, HttpStatus.OK);
			}
			logger.error("Error 404");
			return new ResponseEntity("Update failed...", HttpStatus.NOT_FOUND);
		 }
	  
	  @GetMapping("/viewCrop")
	  public List<CropEntity> viewCrop() throws CropNotFoundException{
		  logger.info("Farmer viewing all crops");
		  return cropService.viewCrop();
	  }
	  
	  
	  @DeleteMapping("/deleteCrop/{cropId}")
	  public String deleteFarmer(@PathVariable long cropId) throws CropNotFoundException  {
		 
		  logger.info("Crop deleted");
		 return "Deleted Crop: " + cropService.deleteCrop(cropId);
	  }
	  
		
	
	

}