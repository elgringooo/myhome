package com.myhome.service;

import java.util.List;

import com.myhome.domain.Car;
import com.myhome.domain.CarItem;

public interface CarManager {

	Car saveCar(Car car);

	List<Car> findAllCars();

	public Car findCar(int id);
	
	public void deleteItem(Car car, CarItem item);

}