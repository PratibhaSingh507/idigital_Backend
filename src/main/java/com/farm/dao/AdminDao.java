package com.farm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farm.model.Admin;



@Repository
public interface AdminDao extends JpaRepository<Admin, Long>{
	List<Admin> findAdminByEmail(String email);
}
