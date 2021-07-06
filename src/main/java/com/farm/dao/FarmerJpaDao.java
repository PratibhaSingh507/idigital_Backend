package com.farm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farm.model.Farmer;

/**
 * This is Farmer Repository interface
 * @author Manoj Chuadhary
 * 
 */

@Repository
public interface FarmerJpaDao extends JpaRepository<Farmer,Integer>{
	@Query("SELECT f FROM Farmer f WHERE f.emailId= :emailId AND f.password= :password")
	List<Farmer> LoginFarmer(@Param("emailId") String emailId, @Param("password") String password);
	
}
