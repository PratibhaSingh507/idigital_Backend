package com.farm.service;
import java.util.List;

import com.farm.model.CropEntity;


public interface CropService {
	
	public CropEntity sellCrop(CropEntity crop);
	public List<CropEntity> viewCrop();
	public CropEntity updateCrop(CropEntity crop);
	public String deleteCrop(long cropId);
	
	
}
