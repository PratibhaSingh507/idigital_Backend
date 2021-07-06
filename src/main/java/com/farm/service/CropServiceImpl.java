package com.farm.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm.model.CropEntity;
import com.farm.dao.CropJpaDao;

@Service
public class CropServiceImpl implements CropService {
	
	@Autowired
	private CropJpaDao cropRepo;
	
	@Override
	public CropEntity sellCrop(CropEntity crop) {
		// TODO Auto-generated method stub
		return cropRepo.save(crop);
	}

	@Override
	public List<CropEntity> viewCrop() {
		// TODO Auto-generated method stub
		return cropRepo.findAll();
	}

	@Override
	public CropEntity updateCrop(CropEntity crop) {
		// TODO Auto-generated method stub
		return cropRepo.save(crop);
	}

	@Override
	public String deleteCrop(long cropId) {
		// TODO Auto-generated method stub
		CropEntity crop=cropRepo.findById(cropId).get();
		cropRepo.deleteById(cropId);
		return crop.toString();
	}
	
	

}
