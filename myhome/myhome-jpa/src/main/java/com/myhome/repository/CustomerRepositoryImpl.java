package com.myhome.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.myhome.domain.Customer;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<Customer> someCustomMethod() {
		Query query = entityManager.createQuery("select c from Customer c");
		List<Customer> resultList = query.getResultList();
		return resultList;

	}

}
