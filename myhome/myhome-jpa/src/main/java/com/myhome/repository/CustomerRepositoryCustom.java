package com.myhome.repository;

import java.util.List;

import com.myhome.domain.Customer;

/**
 * Attention Customer est un mot clé pour spring data!!
 * 
 * @author Thales
 *
 */
public interface CustomerRepositoryCustom {
	public List<Customer> someCustomMethod();
}
