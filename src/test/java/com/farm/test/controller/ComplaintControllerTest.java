package com.farm.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.farm.controller.ComplaintController;
import com.farm.dao.ComplaintJpaDao;
import com.farm.dao.DealerJpaDao;
import com.farm.dao.FarmerJpaDao;
import com.farm.model.Complaint;
import com.farm.model.Dealer;
import com.farm.model.Farmer;
import com.farm.service.ComplaintService;

@RunWith(SpringRunner.class)
@WebMvcTest(ComplaintController.class)
public class ComplaintControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ComplaintService cservice;
	
	@MockBean
	private DealerJpaDao drepo;
	
	@MockBean
	private FarmerJpaDao frepo;
	
	@MockBean
	private ComplaintJpaDao crepo;
	
	@Test
	public void testAddComplaint() throws JsonProcessingException, Exception
	{
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain = new Complaint(1,LocalDateTime.now(), "Cheap Rice", farmer, dealer);
		String inputInJson = this.mapToJson(complain);
		String url = "/faa/complaint/add_complain/1/1";
		String msg = "Your Complaint added successfully. " + 
				"Your Complaint Id is: "+ complain.getCid();
		
		when(cservice.addComplaint(complain,2,2)).thenReturn(complain);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
										.post(url)
										.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String output = response.getContentAsString();
		
		assertThat(output).isEqualTo(msg);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testgetComplainById() throws Exception
	{
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain = new Complaint(1,LocalDateTime.now(), "Cheap Rice", farmer, dealer);
		
		when(cservice.getComplaintById(1)).thenReturn(complain);
		
		String url = "/faa/complaint/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
										.get(url)
										.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = this.mapToJson(complain);
		String actual = result.getResponse().getContentAsString();
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void testGetAllComplaints() throws Exception
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
		
		String url = "/faa/complaint";
		
		when(cservice.getAllComplaint()).thenReturn(complains);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)
										.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = this.mapToJson(complains);
		String actual = result.getResponse().getContentAsString();
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void testGetComplainByFarmer() throws Exception
	{
		Dealer dealer1= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Dealer dealer2= new Dealer(2, "Shraddha", "8765432190", "shraddha@gmail.com","Shraddha@123");
		Farmer farmer = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain1 = new Complaint(1,LocalDateTime.now(), "Unsealed Goods", farmer, dealer1);
		Complaint complain2 = new Complaint(2,LocalDateTime.now(), "Cheap quality rice", farmer, dealer2);

		List<Complaint> complains = new ArrayList<Complaint>();
		complains.add(complain1);
		complains.add(complain2);
		
		HashMap<String, String> expected = new HashMap<String, String>();
		for (Complaint complaint : complains) {
			expected.put(complaint.getDealer().getDealerName(), complaint.getCmessage());
		 }
		
		when(cservice.getComplaintByFarmer(1)).thenReturn(complains);
		String url = "/faa/complaint/farmer/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
										.get(url).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String response = result.getResponse().getContentAsString();
		
		HashMap<String, String> actual = objectMapper.readValue(response, new TypeReference<HashMap<String, String>>(){});
		
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetComplaintByDealer() throws Exception
	{
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer1 = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Farmer farmer2 = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain1 = new Complaint(1,LocalDateTime.now(), "Unsealed Goods", farmer1, dealer);
		Complaint complain2 = new Complaint(2,LocalDateTime.now(), "Cheap quality rice", farmer2, dealer);
	
		List<Complaint> complains = new ArrayList<Complaint>();
		complains.add(complain1);
		complains.add(complain2);
	
		HashMap<String, String> expected = new HashMap<String, String>();
		for (Complaint complaint : complains) {
			expected.put(complaint.getFarmer().getFarmerName(), complaint.getCmessage());
		 }
	
		when(cservice.getComplaintByDealer(1)).thenReturn(complains);
		String url = "/faa/complaint/dealer/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
										.get(url).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String response = result.getResponse().getContentAsString();
		
		HashMap<String, String> actual = objectMapper.readValue(response, new TypeReference<HashMap<String, String>>(){});
		
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void testDeleteComplaint() throws Exception {
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Farmer farmer = new Farmer(2,"8898766433","Rohan","rohangupta@gmail.com","Rohan Gupta","Mumbai","Rohan@123");
		Complaint complain = new Complaint(1,LocalDateTime.now(), "Cheap Rice", farmer, dealer);
		
		String url = "/faa/complaint/delete/1";
		String expected = "Complain Deleted Successfully";
		 when(cservice.deleteComplaint(1)).thenReturn(expected);
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(url);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
}
	
	private String mapToJson(Object object) throws JsonProcessingException{
		return objectMapper.writeValueAsString(object);
	}
}
