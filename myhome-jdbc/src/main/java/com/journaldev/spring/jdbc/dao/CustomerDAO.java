package com.journaldev.spring.jdbc.dao;

import com.journaldev.spring.jdbc.model.Customer;

public interface CustomerDAO {

	public void create(Customer customer);

	public Customer find(int id);
	
	public int count();
}