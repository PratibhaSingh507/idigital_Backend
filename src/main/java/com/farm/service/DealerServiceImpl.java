package com.farm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farm.dao.DealerJpaDao;
import com.farm.model.Dealer;
import com.farm.model.Farmer;
@Service
@Transactional
/**
 * DealerServiceImpl class
 * @author Nisha
 *
 */

public class DealerServiceImpl implements DealerService {
	
	@Autowired
	private DealerJpaDao dealerJpa;

	public static final Logger LOGGER= LoggerFactory.getLogger(DealerServiceImpl.class);
	
	/**
	 * This method takes Dealer details from Controller
	 * and add it to the repository
	 * @param dealer
	 * @return Dealer Object
	 */
	
	@Override
	public List<Dealer> viewDealer() {

		LOGGER.info("Dealer getting all dealers service");
		return dealerJpa.findAll();
	}
	
	@Override
	public Dealer addDealer(final Dealer dealer) {
		LOGGER.trace("Adding Dealer");
		return dealerJpa.save(dealer);
		
	}

	/**
	 * This method takes credentials from controller and then
	 * checks it with repository
	 * @param email
	 * @param password
	 * @return boolean (T/F)
	 */
	
	@Override
	public List<Dealer> loginDealer(final String email, final String password) {
		LOGGER.trace("login Dealer");
		List <Dealer> dealer= dealerJpa.findByDealerEmailIdAndDealerPassword(email, password);
		if(dealer!=null) {
			LOGGER.info("Login Successful");
			return dealer;
		}
		else {
			LOGGER.error(" Login failed ");
			return null;
		}
	
	}
	
	/**
	 * This method takes dealer details from controller
	 * and update it to the repository
	 * @param
	 * @return Dealer Object
	 */

	@Override
	public Dealer updateDealer(Dealer dealer) {
		LOGGER.trace("Updating Dealer");
		Dealer presentDealer=dealerJpa.getOne(dealer.getDealerId());
		if(presentDealer==null) {
			return null;
		}
		else {
			return dealerJpa.save(dealer);                           
			
		}
		
	}

	@Override
	public boolean getDealer(int dealerId) {
		LOGGER.trace("getDealer()");
		if(dealerJpa.findById(dealerId).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public String deleteDealer(int dealerId) {
		// TODO Auto-generated method stub
		LOGGER.info("Dealer delete service");           
		Dealer dealer = dealerJpa.findById(dealerId).get();
		dealerJpa.deleteById(dealerId);
		return dealer.toString();
	
	}

	@Override
	public Dealer getDealerById(int dealerId) {
		// TODO Auto-generated method stub
		LOGGER.info("Dealer get by id Service");
		return dealerJpa.findById(dealerId).orElse(new Dealer(0, "N/A", "N/A", "N/A", "N/A"));


	}
	

	


}