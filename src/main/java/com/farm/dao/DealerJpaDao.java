package com.farm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farm.model.Dealer;
public interface DealerJpaDao extends JpaRepository<Dealer,Integer> {
	/**
	 * This method checks if this email exists
	 * @param email
	 * @param password
	 * @return
	 */
		List<Dealer> findByDealerEmailIdAndDealerPassword(String email,String password);
		

}
