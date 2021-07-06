package com.farm.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farm.exception.AdminException;
import com.farm.exception.AdvertisementNotFoundException;
import com.farm.exception.ResourceNotFoundException;
import com.farm.message.Message;
import com.farm.model.Admin;
import com.farm.model.AdminLogin;
import com.farm.model.Advertisement;
import com.farm.model.Complaint;
import com.farm.model.Dealer;
import com.farm.model.Farmer;
import com.farm.service.AdminService;
import com.farm.service.AdvertisementService;
import com.farm.service.ComplaintService;
import com.farm.service.DealerService;
import com.farm.service.FarmerService;


@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "*")

public class AdminController {
	//api-docs
	//swagger-ui.html
	@Autowired
	private AdminService admService;
	@Autowired
	private FarmerService farmerService;
	@Autowired
	private DealerService dealerService;
	@Autowired
	private ComplaintService cservice;
	@Autowired
	private AdvertisementService aservice;
	
    private static final Logger logger = LogManager.getLogger(AdminController.class);

	
	// get all the admin
	@GetMapping("/getAll")
	public List<Admin> getAllAdmin() throws AdminException{
		
	    logger.info("Getting All admin ");

		return admService.getAllAdmin();
	}

	// get admin by email id
	@GetMapping("/get/{email}")
	public ResponseEntity<?> getAdminByEmail(@PathVariable("email") String email) throws AdminException{
		try {
			List<Admin> cand = admService.getAdminByEmail(email);
			return ResponseEntity.ok(cand); // return 200, with json body
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find admin with email " + email);
		}
	}

	// get details by id
	@GetMapping(value = "/{id}")
	public ResponseEntity getAdminById(@PathVariable long id) throws AdminException{
		 logger.info("Find user by ID");
		
		
		Admin admin = admService.getAdminById(id);
		if (admin == null)
			return new ResponseEntity<String>("Admin Not Found ", HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Admin>(admin, HttpStatus.OK);

	}

	// create Admin
	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> save(@Valid @RequestBody Admin admin) throws AdminException {

		 logger.info("Creating admin");
		 logger.info("in Admin Authentication Controller");
		admService.createAdmin(admin);
		return new ResponseEntity<String>("Admin Created Successfully ", HttpStatus.OK);
		
	}

	// Admin login
	@PostMapping(value = "/login")
	public ResponseEntity<String> adminLogin(@RequestBody AdminLogin login) throws AdminException{
		 logger.info( "Login:");
		
	
		if (admService.adminLogin(login.getEmail(), login.getPassword()))
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Login Failed)", HttpStatus.BAD_REQUEST);
	}
	//View farmer
	
	@GetMapping("/getFarmer")
	public List<Farmer> viewFarmer() {

		logger.info("admin searching all Farmers");
		return farmerService.viewFarmer();
	}
	
	@GetMapping("/getDealer")
	public List<Dealer> viewDealer() {

		logger.info("admin searching all Dealers");
		return dealerService.viewDealer();
	}
	
	@GetMapping("/getComplaint")
	public List<Complaint> getAllComplaints()
	{
		  logger.info("admin searching All Complaint ");
		return cservice.getAllComplaint();
	}
	
	}




