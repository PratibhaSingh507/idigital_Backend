package com.farm.test.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.farm.dao.DealerJpaDao;
import com.farm.model.Dealer;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DealerDaoTest {

	@Autowired
	private DealerJpaDao dealerJpaDao;

	@Test
	public void testNewDealer() throws Exception {
		Dealer dealer= new Dealer(1, "sneha", "9876598765", "sneha@gmail.com","Sneha@123");
		Dealer saveInDb = dealerJpaDao.saveAndFlush(dealer);
		Dealer getFromInDb = dealerJpaDao.findById(saveInDb.getDealerId()).get();

		assertThat(getFromInDb).isEqualTo(saveInDb);

	}

	@Test
	public void testGetAllDealers() throws Exception {
		List<Dealer> dealer = dealerJpaDao.findAll();
		long rowsInDb = dealerJpaDao.count();
	
		assertThat(dealer.size()).isEqualTo(rowsInDb);
	}

}
