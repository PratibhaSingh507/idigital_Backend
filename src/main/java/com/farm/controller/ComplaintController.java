package com.farm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.dao.ComplaintJpaDao;
import com.farm.exception.ComplaintException;
import com.farm.model.Complaint;
import com.farm.service.ComplaintService;

	@RestController
	@RequestMapping("/complaint")
	@CrossOrigin(origins = "*")
	public class ComplaintController {

		@Autowired
		private ComplaintService cservice;
		
		@Autowired
		private ComplaintJpaDao crepo;
		  private static final Logger logger = LogManager.getLogger(ComplaintController.class);

		
		@PostMapping("/add_complaint/{farmer_id}/{dealer_id}")
		public ResponseEntity<String> addComplaint(	@Valid @RequestBody Complaint complaint, 
									@PathVariable("farmer_id")int farmer_id, 
									@PathVariable("dealer_id") int dealer_id) throws ComplaintException 
		{	
			  logger.info("Adding Complaint ");
			 cservice.addComplaint(complaint, farmer_id, dealer_id);
			 
			 String msg = "Your Complaint added successfully. " + 
						"Your Complaint Id is: "+ complaint.getCid();
				 return ResponseEntity.ok(msg);
		}

		@GetMapping("/{cid}")
		public ResponseEntity<Complaint> getComplaintById(@PathVariable("cid")int cid) throws ComplaintException
		{
			
			  logger.info("Getting Complaint by Id ");
			Complaint c = cservice.getComplaintById(cid);
			if(c == null)
			{
				throw new ComplaintException("Complaint Not Found.");
			}
			return ResponseEntity.ok(c);
		}
		
		@GetMapping("/farmer/{fid}")
		public ResponseEntity<Object> getComplaintByFarmer(@PathVariable("fid")int fid)
		{
			  logger.info("Getting Complaint by Farmer Id ");
			HashMap<String, String> lists = new HashMap<String, String>();
			List<Complaint> complains = cservice.getComplaintByFarmer(fid);
			for (Complaint complaint : complains) {
				lists.put(complaint.getDealer().getDealerName(), complaint.getCmessage());
			 }
			if(lists.size()<=0)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Complaint from selected Farmer.");
			}
				
			return ResponseEntity.status(HttpStatus.OK).body(lists);
		} 
		
		@GetMapping("/dealer/{did}")
		public ResponseEntity<Object> getComplaintByDealer(@PathVariable("did")int did)
		{
			  logger.info("Getting Complaint by Dealer Id ");
			List<Complaint> complains = cservice.getComplaintByDealer(did);
			HashMap<String, String> lists = new HashMap<String, String>();
			for (Complaint complaint : complains) {
				lists.put(complaint.getFarmer().getFarmerName(), complaint.getCmessage());
			}
			if(lists.size()<=0)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Complaint Not Found for selected Dealer.");
			}		
			return ResponseEntity.status(HttpStatus.OK).body(lists);
		} 
		
		@GetMapping("/getAll")
		public List<Complaint> getAllComplaints()
		{
			  logger.info("Getting All Complaint ");
			return cservice.getAllComplaint();
		}
		
		@DeleteMapping("/delete/{cid}")
		public String deleteComplaint(@PathVariable("cid")int cid) throws ComplaintException 
		{
			  logger.info("Deleting complaint By Id ");
			return cservice.deleteComplaint(cid);
		}
	}



