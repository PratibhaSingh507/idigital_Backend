package com.farm.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.farm.dao.AdvertisementJpaDao;
import com.farm.exception.AdvertisementNotFoundException;
import com.farm.exception.ComplaintException;
import com.farm.exception.CropNotFoundException;
import com.farm.exception.DealerNotFoundException;
import com.farm.message.Message;
import com.farm.model.Advertisement;
import com.farm.model.Complaint;
import com.farm.model.CropEntity;
import com.farm.model.Dealer;
import com.farm.service.AdvertisementService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/advertisement")
@CrossOrigin(origins="http://localhost:3000")

public class AdvertiseController {

	@Autowired
	private AdvertisementService aservice;
	
	@Autowired
	private AdvertisementJpaDao arepo;
	 private static final Logger logger = LogManager.getLogger(AdvertisementService.class);		
	
	
	@PostMapping("/post/{dealer_Id}")
	public ResponseEntity<String> addAdvertisement(@Valid @RequestBody Advertisement advertisement, @PathVariable("dealer_Id") int dealer_Id) throws AdvertisementNotFoundException 
	{    

	    logger.trace("Adding Advertisement");
	    aservice.addAdvertisement(advertisement, dealer_Id);
			 
			 String msg = "Your Advertisement added successfully. " + 
						"Your Advertisement Id is: "+ advertisement.getAid();
				 return ResponseEntity.ok(msg);
		}
	
	@DeleteMapping("/delete/{aid}")
	public String deleteAdvertisement(@PathVariable ("aid") int aid)throws AdvertisementNotFoundException
	{
	    logger.trace("Deleting Advertisement");
		
		return aservice.deleteAdvertisement(aid);	
	 
	}
	
	@GetMapping("/alladv")
	public ResponseEntity<Message> getAdvertisementList()throws AdvertisementNotFoundException {
	    logger.trace("view all advertisements");
		Message msg=new Message();
		msg.setResMessage("Successfully returning of list");
		msg.setAdvList(aservice.getAdvertisementList());
		List<Advertisement> advList= aservice.getAdvertisementList();
		if(!advList.isEmpty()) {
			return new ResponseEntity<Message>(msg,HttpStatus.OK);
		}else {
			logger.error("No Advertisements Found");
			throw new AdvertisementNotFoundException("No Advertisements Found");
		}
	}
	
	@GetMapping("/ViewAdbyId/{dealer_Id}")
	public ResponseEntity<Message> viewAdvertise(@Valid @PathVariable("dealer_Id") int dealer_Id) throws AdvertisementNotFoundException {
		logger.trace("view dealers all advertisements");
		Message msg=new Message();
		msg.setResMessage("returning AdvertisementList");
		msg.setAdvList(aservice.viewAdvertise(dealer_Id));
		return new ResponseEntity<Message>(msg, HttpStatus.OK);
	}
	
		
		@PutMapping("/updateAd")
		 public ResponseEntity<Advertisement> updateAd (@RequestBody Advertisement advertisement) throws AdvertisementNotFoundException{
				logger.trace("Entering into method updateAd");
				
				boolean saveAdvertise=aservice.getAdvertisement(advertisement.getAid());
				if(saveAdvertise) {
					Advertisement updateAd=aservice.updateAd(advertisement);
					return new ResponseEntity<Advertisement>(updateAd,HttpStatus.OK);
				}
				else {
					logger.error("Advertise cannot be updated, as id"+advertisement.getAid()+"not present");
					throw new AdvertisementNotFoundException("Dealer cannot be updated"+advertisement.getAid()+"not present");
				}
		}	
		
	
}