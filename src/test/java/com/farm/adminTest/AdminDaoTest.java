package com.farm.adminTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.farm.dao.AdminDao;
import com.farm.model.Admin;



@RunWith(SpringRunner.class)
@DataJpaTest
public class AdminDaoTest {

	@Autowired
	private AdminDao adminDao;

	@Test
	public void testNewAdmin() throws Exception {
		Admin admin = new Admin(1,"pranesh@gmail.com", "Manlite@24");
		Admin saveInDb = adminDao.saveAndFlush(admin);
		Admin getFromInDb = adminDao.findById(saveInDb.getId()).get();

		assertThat(getFromInDb).isEqualTo(saveInDb);

	}

	@Test
	public void testGetAllAdmins() throws Exception {
		List<Admin> admin = adminDao.findAll();
		long rowsInDb = adminDao.count();
	
		assertThat(admin.size()).isEqualTo(rowsInDb);
	}

}
