package com.journaldev.spring.jdbc.service;

import com.journaldev.spring.jdbc.model.Customer;

public interface CustomerManager {

	
	public void createCustomer(Customer cust) throws Exception;
	
	
	public Customer findCustomer(int id);


	int countCustomer();


	int countAddress();
}
