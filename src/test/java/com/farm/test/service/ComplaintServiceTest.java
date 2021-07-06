package com.farm.test.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.farm.dao.ComplaintJpaDao;
import com.farm.exception.ComplaintException;
import com.farm.model.Complaint;
import com.farm.model.Dealer;
import com.farm.model.Farmer;
import com.farm.service.ComplaintService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplaintServiceTest {

	@Autowired
	private ComplaintService cservice;
	
	@MockBean
	private ComplaintJpaDao crepo;
	
	@Test
	public void testAddComplaint() throws Exception
	{
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain = new Complaint(1,LocalDateTime.now(), "Unsealed Goods", farmer, dealer);

		when(crepo.saveAndFlush(complain)).thenReturn(complain);
		assertEquals(complain, cservice.addComplaint(complain,1,1));
	}
	
	@Test
	public void testGetComplainById()
	{
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain = new Complaint(1,LocalDateTime.now(), "Unsealed Goods", farmer, dealer);
		
		when(crepo.findByCid(1)).thenReturn(complain);
		assertEquals(complain, cservice.getComplaintById(1));
	}
	
	@Test
	public void testGetAllComplain()
	{
		Dealer dealer1= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer1 = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain1 = new Complaint(1,LocalDateTime.now(), "Unsealed Goods", farmer1, dealer1);
		
		Dealer dealer2= new Dealer(2, "Shraddha", "8765432190", "shraddha@gmail.com","Shraddha@123");
		Farmer farmer2 = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain2 = new Complaint(1,LocalDateTime.now(), "Cheap  quality rice", farmer2, dealer2);
		
		List<Complaint> complains = new ArrayList<Complaint>();
		complains.add(complain1);
		complains.add(complain2);
		
		when(crepo.findAll()).thenReturn(complains);
		assertEquals(complains, cservice.getAllComplaint());	
	}
	
	@Test
	public void testGetComplaintByFarmer()
	{
		Dealer dealer1= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Dealer dealer2= new Dealer(2, "Shraddha", "8765432190", "shraddha@gmail.com","Shraddha@123");
		Farmer farmer = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain1 = new Complaint(1,LocalDateTime.now(), "Unsealed Goods", farmer, dealer1);
		Complaint complain2 = new Complaint(2,LocalDateTime.now(), "Cheap quality rice", farmer, dealer2);

		List<Complaint> complains = new ArrayList<Complaint>();
		complains.add(complain1);
		complains.add(complain2);
		
		when(crepo.getByFarmer(1)).thenReturn(complains);
		assertEquals(complains, cservice.getComplaintByFarmer(1));
	}
	
	@Test
	public void testGetComplaintByDealer()
	{
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer1 = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Farmer farmer2 = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain1 = new Complaint(1,LocalDateTime.now(), "Unsealed Goods", farmer1, dealer);
		Complaint complain2 = new Complaint(2,LocalDateTime.now(), "Cheap quality rice", farmer2, dealer);
		
		List<Complaint> complains = new ArrayList<Complaint>();
		complains.add(complain1);
		complains.add(complain2);
		
		when(crepo.getByDealer(1)).thenReturn(complains);
		assertEquals(complains, cservice.getComplaintByDealer(1));
		
	}
	
}