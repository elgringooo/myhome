package com.myhome.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.myhome.domain.Customer;

/**
 * Classe de test du repository.
 * 
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:conf-test/application-context.xml" })
// Configuration avec annotation Config
// @ContextConfiguration(classes = Config.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CustomerRepositoryTest {

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomerRepositoryTest.class);

	@Autowired
	CustomerRepository clientRepo;

	@BeforeClass
	public static void BeforeClass() {

	}

	@Before
	public void setup() {
	    LOG.info("GOO");
		// When executing this method setUp
		// The transaction will be rolled back after rollBackTrue Test
		// The transaction will not be rolled back after rollBackFalse Test
		// Context
		List<Customer> customers = new ArrayList<Customer>();
		Customer client;
		int size = 10;
		for (int i = 0; i < size; i++) {
			client = new Customer();
			client.setLastName("TOTO" + i);
			client.setFirstName("titi" + i);
			client.setEmailAddress(String.format("%s.%s@gmail.com",
					client.getFirstName(), client.getLastName()));
			customers.add(client);
		}
		clientRepo.save(customers);

	}

	@Test
	@Rollback(true)
	public void tstFindClientByName() {
		final Customer client1 = new Customer();

		client1.setLastName("TOTO");
		client1.setFirstName("titi");
		Customer client = clientRepo.save(client1);

		Assert.assertTrue(client.getId() > 0);
		// List<Customer> founds=customerRepo.findAll();
		List<Customer> founds = clientRepo
				.findByLastNameLikeOrderByLastNameAsc("TOT%");
		LOG.debug(String.format("founds %s", founds));
		Assert.assertTrue(founds.size() > 0);

		Customer found = clientRepo.findOne(client.getId());

		Assert.assertNotNull(found);
	}

	@Test
	@Rollback(true)
	public void testFindAllPageable() {

		LOG.debug(String.format("Size %s", clientRepo.findAll().size()));

		int pageSize = 5;
		int pageNumber = 0;
		Pageable pageable = new PageRequest(pageNumber, pageSize);

		Page<Customer> pageOfCustomers = clientRepo.findAll(pageable);

		LOG.debug(String.format("Customers from page %s , size %s = ",
				pageNumber, pageSize, clientRepo.findAll().size()));
		Assert.assertEquals(pageSize, pageOfCustomers.getContent().size());
	}

	@Test
	@Rollback(true)
	public void testQuery() {
		Customer customer = clientRepo
				.findByEmailAddress("titi0.TOTO0@gmail.com");
		Assert.assertNotNull(customer);

	}

	@Test
	@Rollback(true)
	public void testFindByNamedParameter() {
		List<Customer> customers = clientRepo.someCustomMethod();
		Customer customer = clientRepo.findByNamedParameter("TOTO0", "titi0");
		Assert.assertNotNull(customer);

	}

	@Test
	@Rollback(true)
	public void testCustomerImplementation() {
		List<Customer> customers = clientRepo.someCustomMethod();
		Assert.assertNotNull(customers);
		Assert.assertTrue(customers.size() > 0);
	}

}
