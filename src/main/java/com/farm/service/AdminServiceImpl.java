package com.farm.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm.dao.AdminDao;
import com.farm.exception.ResourceNotFoundException;
import com.farm.model.Admin;


@Service
@Transactional
public class AdminServiceImpl implements AdminService{


	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Admin createAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.save(admin);
	}

	@Override
	public boolean adminLogin(String email, String password) {
		// TODO Auto-generated method stub
		Boolean found = false;
		if (email == null) {
			found = false;
		}
		List<Admin> admin = adminDao.findAll();
		for (Admin admins : admin) {
			if ((admins.getEmail().equals(email)) && (admins.getPassword().equals(password))) {
				found = true;
				break;
			}
		}
		return found;
	}

	@Override
	public List<Admin> getAllAdmin() {
		// TODO Auto-generated method stub
		List<Admin> admin = adminDao.findAll();
		return admin; 
	}

	@Override
	public Admin getAdminById(long id) {
		// TODO Auto-generated method stub
		Optional<Admin> candResponse = adminDao.findById(id);
		Admin admin = candResponse.get();
		return admin;
	}

	@Override
	public List<Admin> getAdminByEmail(String email) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		List<Admin> adm = adminDao.findAdminByEmail(email);
		if (adm == null)
			throw new ResourceNotFoundException("Cannot find admin with email " + email);
		else
			return adm;
	}
}


