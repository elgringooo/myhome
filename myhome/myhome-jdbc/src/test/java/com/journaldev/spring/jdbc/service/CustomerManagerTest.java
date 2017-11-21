package com.journaldev.spring.jdbc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.journaldev.spring.jdbc.model.Address;
import com.journaldev.spring.jdbc.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class CustomerManagerTest {
	@Autowired
	private CustomerManager customerManager;

	@Test
	public void create() {
		Customer cust = createDummyCustomer();
		try {
			customerManager.createCustomer(cust);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Customer createDummyCustomer() {
		Customer customer = new Customer();
		customer.setId(2);
		customer.setName("Pankaj");
		Address address = new Address();
		address.setId(2);
		address.setCountry("India");
		// setting value more than 20 chars, so that SQLException occurs
		address.setAddress("Albany Dr, San Jose, CA 95129");
		customer.setAddress(address);
		return customer;
	}
}
