package com.journaldev.spring.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.jdbc.dao.AddressDAO;
import com.journaldev.spring.jdbc.dao.CustomerDAO;
import com.journaldev.spring.jdbc.model.Customer;

@Service(value = "customerManager")
public class CustomerManagerImpl implements CustomerManager {
	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private AddressDAO addressDAO;

	@Override
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	public void createCustomer(Customer cust) throws Exception {
		customerDAO.create(cust);
		if (true)
			throw new Exception("blab");
		addressDAO.create(cust.getAddress());

	}

	@Override
	@Transactional(readOnly = true)
	public Customer findCustomer(int id) {
		return customerDAO.find(id);
	}

	@Override
	@Transactional(readOnly = false)
	public int countCustomer() {
		return customerDAO.count();
	}

	@Override
	@Transactional(readOnly = false)
	public int countAddress() {
		return addressDAO.count();
	}

}
