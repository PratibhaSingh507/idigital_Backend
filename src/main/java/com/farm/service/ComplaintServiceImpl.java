package com.farm.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm.dao.*;
import com.farm.exception.ComplaintException;
import com.farm.model.*;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {
	
	@Autowired
	private ComplaintJpaDao crepo;
	
	@Autowired
	private FarmerJpaDao frepo;
	
	@Autowired
	private DealerJpaDao drepo;
	
	@Override
	public Complaint addComplaint(Complaint complaint, int farmer_id, int dealer_id) throws ComplaintException {
		
		
		Farmer farmer = frepo.findById(farmer_id)
		 		.orElseThrow(()-> new ComplaintException("Farmer not found."));
		Dealer dealer = drepo.findById(dealer_id)
		 		.orElseThrow(()-> new ComplaintException("Dealer not found."));
		
		complaint.setDealer(dealer);
		complaint.setFarmer(farmer);
		complaint.setC_date(LocalDateTime.now());
		
		return crepo.saveAndFlush(complaint);
	}

	@Override
	public Complaint getComplaintById(int cid) {
		return crepo.findByCid(cid);
	}

	@Override
	public List<Complaint> getAllComplaint() {
		return crepo.findAll();
	}

	@Override
	public List<Complaint> getComplaintByFarmer(int fid) {
		List<Complaint> complains = crepo.getByFarmer(fid);
		 return complains;
	}

	@Override
	public List<Complaint> getComplaintByDealer(int did) {
		List<Complaint> complains = crepo.getByDealer(did);
		 return complains;
	}

	@Override
	public String deleteComplaint(int cid) throws ComplaintException {
		crepo.findById(cid)
		.orElseThrow(()->new ComplaintException("Complaint not found."));
		crepo.deleteById(cid);
		return "Complaint Deleted Successfully.";
	}

	

}
