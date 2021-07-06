package com.farm.test.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.farm.dao.FarmerJpaDao;
import com.farm.model.Farmer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FarmerJpaDaoTest {

	@Autowired
	private FarmerJpaDao farmerJpaDao;

	@Test
	public void testNewFarmer() throws Exception {

		Farmer farmer = new Farmer();
		farmer.setFarmerId(1);
		farmer.setFarmerName("preet singh");
		farmer.setEmailId("spreet942@gmail.com");
		farmer.setPassword("spreet@123");
		farmer.setLocation("Mumbai");
		farmer.setCropName("Whaet");
		farmer.setContactNo("9987654322");
		
		Farmer saveInDb = farmerJpaDao.save(farmer);

		Farmer getFromInDb = farmerJpaDao.findById(saveInDb.getFarmerId()).get();

		assertThat(getFromInDb).isEqualTo(saveInDb);

	}

	@Test
	public void testGetAllfarmer() throws Exception {
		List<Farmer> farmer = farmerJpaDao.findAll();
		long rowsInDb = farmerJpaDao.count();

		assertThat(farmer.size()).isEqualTo(rowsInDb);
	}
}
