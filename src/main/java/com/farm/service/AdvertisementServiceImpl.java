package com.farm.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.farm.dao.AdvertisementJpaDao;
import com.farm.dao.DealerJpaDao;
import com.farm.exception.AdvertisementNotFoundException;
import com.farm.model.Advertisement;
import com.farm.model.Dealer;
@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {
	@Autowired
	private AdvertisementJpaDao arepo;
	@Autowired
	private DealerJpaDao drepo;
	
	public static final Logger LOGGER= LoggerFactory.getLogger(AdvertisementServiceImpl.class);

	@Override
	public Advertisement addAdvertisement(Advertisement advertisement, int dealer_Id) throws AdvertisementNotFoundException
	{
		//LOGGER.info("Adding Advertisement");
		Dealer dealer = drepo.findById(dealer_Id)
		 		.orElseThrow(()-> new AdvertisementNotFoundException("Dealer not found."));
		advertisement.setDealer(dealer);		
		arepo.save(advertisement);	
		return arepo.saveAndFlush(advertisement);
	}


	@Override
	public String deleteAdvertisement(int aid) {
		LOGGER.info("Deleting Advertisement");
		arepo.deleteById(aid);
		//List <Advertisement> advertisementList = arepo.findAll();
		return "advertise deleted successfully";
	}


	

	@Override
	public List<Advertisement> getAdvertisementList() {
		LOGGER.trace("getAllAdvertisement()");
		return arepo.findAll();
	}


	@Override
	public Advertisement updateAd(Advertisement advertisement) {
		LOGGER.trace("Updating Advertisement");
		Advertisement Ad=arepo.getOne(advertisement.getAid());
		if(Ad==null) {
			return null;
		}
		else {
			return arepo.save(advertisement);
			
		}
		
	}


	@Override
	public boolean getAdvertisement(int aid) {
		// TODO Auto-generated method stub
		LOGGER.trace("getAdvertisement()");
		if(arepo.findById(aid).isPresent()) {
			return true;
		}
		return false;
	}


	@Override
	public List<Advertisement> viewAdvertise(int dealer_Id) {
		// TODO Auto-generated method stub
		LOGGER.info("Viewing Advertisement by Dealer id");
		 Dealer d=drepo.getOne(dealer_Id);
		// List<Advertisement> list=feedbackRepository.findByDealer(m);
		List<Advertisement> list=arepo.findByDealer(d);
		return list;
	}


	

		
	}