package com.farm.service;

import java.util.List;

import com.farm.exception.ResourceNotFoundException;
import com.farm.model.Admin;


public interface AdminService {

	Admin createAdmin(Admin admin);

	boolean adminLogin(String email, String password);

	List<Admin> getAllAdmin();

	Admin getAdminById(long id);
	
	List<Admin> getAdminByEmail(String email) throws ResourceNotFoundException;
}
