package com.farm.service;

import java.util.List;
import com.farm.exception.ComplaintException;
import com.farm.model.Complaint;


	public interface ComplaintService {

		public Complaint addComplaint(Complaint complaint, int farmer_id, int dealer_id) throws ComplaintException;
		public Complaint getComplaintById(int cid);
		public List<Complaint> getAllComplaint();
		public List<Complaint> getComplaintByFarmer(int fid);
		public List<Complaint> getComplaintByDealer(int did);
		public String deleteComplaint(int cid) throws ComplaintException;
	}



