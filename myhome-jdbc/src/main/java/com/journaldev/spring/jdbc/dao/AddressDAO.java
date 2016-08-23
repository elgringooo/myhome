package com.journaldev.spring.jdbc.dao;

import com.journaldev.spring.jdbc.model.Address;
import com.journaldev.spring.jdbc.model.Customer;

public interface AddressDAO {

	public void create(Address address);

	public Address find(int id);
	
	public int count();
}