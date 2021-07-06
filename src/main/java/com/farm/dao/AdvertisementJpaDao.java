package com.farm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.model.Advertisement;
import com.farm.model.Complaint;
import com.farm.model.Dealer;


public interface AdvertisementJpaDao extends JpaRepository<Advertisement, Integer> {

	List<Advertisement> findByDealer(Dealer d);
}