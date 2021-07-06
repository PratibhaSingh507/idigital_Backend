package com.farm.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.farm.exception.AdvertisementNotFoundException;
import com.farm.exception.DealerNotFoundException;
import com.farm.exception.FarmerNotFoundException;
import com.farm.message.Message;
import com.farm.model.Advertisement;
import com.farm.model.Dealer;
import com.farm.model.Farmer;
import com.farm.service.AdvertisementService;
import com.farm.service.DealerService;

/**
 **Dealer Controller Class
 * @author Nisha
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/dealer")

public class DealerController {
@Autowired
	private DealerService dealerService;
@Autowired
private AdvertisementService aservice;

public static final Logger LOGGER = LoggerFactory.getLogger(DealerController.class);

/**
 * It accepts dealer details from dealer and pass it to 
 * service layer addDealer() method.
 * @param dealer
 * @return Dealer Object
 */


@PostMapping("/add")
public ResponseEntity<Dealer> addDealer(@RequestBody Dealer dealer) {
	// LOGGER.trace("Adding Dealer");
	Dealer saveDealer = dealerService.addDealer(dealer);
	
	if(saveDealer == null) {
		//LOGGER.error("Error 404");
		return new ResponseEntity("Registeration Failed",HttpStatus.NOT_FOUND);
	
	}
	//LOGGER.info("Dealer Added");
	return new ResponseEntity<Dealer>(saveDealer,HttpStatus.OK);
}

/**
 * This method accepts login credentials from dealer
 * and pass that credentials to service layer loginDealer() method
 * @param dealer
 * @return
 * @throws DealerNotFoundException
 */

@PostMapping("/login")
public ResponseEntity<Dealer> dealerLogin(@RequestBody Dealer dealer) throws DealerNotFoundException {
	// LOGGER.trace("Login Dealer");
	List <Dealer> login=dealerService.loginDealer(dealer.getDealerEmailId(),dealer.getDealerPassword());
		    if(login.isEmpty()) {	
		    	LOGGER.error("Error 404");
	    	return new ResponseEntity("Login Failed",HttpStatus.NOT_FOUND);
	    	
	    }
	    else  {
	    	LOGGER.info("Dealer Login Successful");
	    	return new ResponseEntity("Login Successful",HttpStatus.OK);
	    }
}

@PutMapping("/update")
public ResponseEntity<Dealer> updateDealer(@RequestBody Dealer dealer) throws DealerNotFoundException {
	LOGGER.trace("Dealer updates details");
	boolean saveDealer=dealerService.getDealer(dealer.getDealerId());
	if(saveDealer) {
		Dealer updateDealer=dealerService.updateDealer(dealer);
		return new ResponseEntity<Dealer>(updateDealer,HttpStatus.OK);
	}
	else {
		LOGGER.error("Dealer cannot be updated, as id"+dealer.getDealerId()+"not present");
		throw new DealerNotFoundException("Dealer cannot be updated"+dealer.getDealerId()+"not present");
	}
}


@GetMapping("/viewDealer")
public List<Dealer> viewDealer() {

//	LOGGER.info("admin searching all Dealers");
	return dealerService.viewDealer();
}
@GetMapping("/viewDealer/{dealerId}") 
public Dealer getDealerById(@PathVariable int dealerId) throws DealerNotFoundException 
{

LOGGER.info("dealer get by Id"); 
return dealerService.getDealerById(dealerId);
}



@DeleteMapping("/deleteDealer/{dealerId}")
public String deleteDealer(@PathVariable int dealerId) throws DealerNotFoundException {

	LOGGER.info("Dealer deleted");
	return "Deleted Dealer: " + dealerService.deleteDealer(dealerId);
}


}