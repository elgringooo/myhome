package com.myhome.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.myhome.dao.CarDao;
import com.myhome.domain.Car;

@Repository
public class CarDaoImpl extends GenericDaoImpl<Car, Integer> implements CarDao {

	private static final Logger LOG = LoggerFactory.getLogger(CarDaoImpl.class);

	@PersistenceContext
	protected EntityManager entityManager;

	public List<Car> getCars() {
		Query query = entityManager.createQuery("select c from Car c");
		List<Car> resultList = query.getResultList();
		return resultList;
	}

	public Car getCar(Long carId) {
		return entityManager.find(Car.class, carId);
	}

}