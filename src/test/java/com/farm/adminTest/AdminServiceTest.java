package com.farm.adminTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.farm.dao.AdminDao;
import com.farm.model.Admin;
import com.farm.service.AdminService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

	@MockBean
	private AdminDao adminJpaDao;

	@Autowired
	private AdminService adminService;

	@Test
	public void testAddAdmin() {
		Admin admin = new Admin();
		admin.setId(3);
		admin.setEmail("pranesh@gmail.com");
		admin.setPassword("Manlite@24");

		Mockito.when(adminJpaDao.save(admin)).thenReturn(admin);
		assertThat(adminService.createAdmin(admin)).isEqualTo(admin);
	}

	@Test
	public void testGetAllAdmin() {
		Admin admin = new Admin();
		admin.setId(3);
		admin.setEmail("pranesh@gmail.com");
		admin.setPassword("Manlite@24");

		Admin admin1 = new Admin();
		admin1.setId(4);
		admin1.setEmail("dheena@gmail.com");
		admin1.setPassword("Dheena@123");

		List<Admin> adminList = new ArrayList<>();
		adminList.add(admin1);
		adminList.add(admin1);

		Mockito.when(adminJpaDao.findAll()).thenReturn(adminList);
		assertThat(adminService.getAllAdmin()).isEqualTo(adminList);

	}

}