package com.farm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farm.model.Dealer;
import com.farm.model.Farmer;
@Service
public interface DealerService {
	/**
	 * This method takes Dealer details from Controller
	 * and add it to the repository
	 * @param dealer
	 * @return Dealer Object
	 */
	
	public Dealer addDealer(Dealer dealer);
	
	/**
	 * This method takes credentials from controller and then
	 * checks it with repository
	 * @param email
	 * @param password
	 * @return boolean (T/F)
	 */
	
	List <Dealer> loginDealer(String email,String password);
	
	/**
	 * This method takes dealer details from controller
	 * and update it to the repository
	 * @param dealer
	 * @return List<Dealer>
	 */
	Dealer updateDealer(Dealer dealer);
	
	boolean getDealer(int dealerId);
	
	public List<Dealer> viewDealer();
	public String deleteDealer(int dealerId);
	public Dealer getDealerById(int dealerId);
	

}