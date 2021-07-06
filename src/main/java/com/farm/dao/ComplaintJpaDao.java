package com.farm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farm.model.Complaint;

public interface ComplaintJpaDao extends JpaRepository<Complaint, Integer>{

		@Query(value = "SELECT *  FROM complaint c WHERE c.farmer_id= :farmerId", nativeQuery = true)
		public List <Complaint> getByFarmer(@Param("farmerId") int farmer_id);

		@Query(value = "SELECT *  FROM complaint c WHERE c.dealer_Id= :dealer_Id", nativeQuery = true)
		public List<Complaint> getByDealer(@Param("dealer_Id") int did);

		public Complaint findByCid(int cid);
	}



