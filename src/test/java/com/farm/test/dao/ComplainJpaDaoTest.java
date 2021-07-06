package com.farm.test.dao;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.farm.dao.ComplaintJpaDao;
import com.farm.model.Complaint;
import com.farm.model.Dealer;
import com.farm.model.Farmer;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ComplainJpaDaoTest {

	@Autowired
	private ComplaintJpaDao crepo;
	
	
	@Test
	public void testSaveComplaint() {
		
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain = new Complaint(1,LocalDateTime.now(), "Bad Rice", farmer, dealer);
		Complaint saveInDb = crepo.saveAndFlush(complain);
		Complaint getFromDb =  crepo.findById(saveInDb.getCid()).get();
		
		assertThat(getFromDb).isEqualTo(saveInDb);
	}
	
	@Test
	public void testGetByComplaintId()
	{
		int id = 1;
		Complaint getFromDb = crepo.findByCid(id);
		
		assertThat(getFromDb.getCid()).isEqualTo(id);		
	}
	
	@Test
	public void testGetAllComplaints()
	{
		List<Complaint> complains = crepo.findAll();
		long rowsInDb = crepo.count();
	
		assertThat(complains.size()).isEqualTo(rowsInDb);
	}
	
	@Test
	public void testGetByFarmer()
	{
		int farmer_id = 1;
		List<Complaint> complains = crepo.getByFarmer(farmer_id);
		
		assertThat(complains.size()).isNotEqualTo(0);
		
	}
	
	@Test
	public void testGetByDealer()
	{
		int dealer_id = 1;
		List<Complaint> complains = crepo.getByDealer(dealer_id);
		
		assertThat(complains.size()).isNotEqualTo(0);
	}
}
