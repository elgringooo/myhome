package com.myhome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.CarDao;
import com.myhome.domain.Car;
import com.myhome.domain.CarItem;
import com.myhome.service.CarManager;

@Service
public class CarManagerImpl implements CarManager {

	@Autowired
	private CarDao carDao;

	@Transactional
	public Car saveCar(Car car) {
		return carDao.saveOrUpdate(car);
	}

	@Transactional(readOnly = true)
	public List<Car> findAllCars() {
		return carDao.findAll();
	}

	@Transactional(readOnly = true)
	public Car findCar(int id) {
		return carDao.find(id);
	}

	@Transactional
	public void deleteItem(Car car, CarItem item) {
		car.getItems().remove(item);
		
	}
	 
	
	

}