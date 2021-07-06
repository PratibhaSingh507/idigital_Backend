package com.farm.service;

import java.util.List;

import javax.validation.Valid;
import com.farm.exception.AdvertisementNotFoundException;
import com.farm.model.Advertisement;
import com.farm.model.Complaint;
import com.farm.model.CropEntity;
import com.farm.model.Dealer;

public interface AdvertisementService {

	public Advertisement addAdvertisement( Advertisement advertisement,int dealer_Id)throws AdvertisementNotFoundException;
	public String deleteAdvertisement(int aid);
	List<Advertisement> getAdvertisementList();
	public List<Advertisement> viewAdvertise(int dealer_Id);//particular dealers advertise
	public Advertisement updateAd(Advertisement advertisement);
	boolean getAdvertisement(int aid);
}