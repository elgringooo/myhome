package com.myhome.dao;

import java.util.List;

import com.myhome.domain.Car;

public interface CarDao extends GenericDao<Car, Integer> {
    public List<Car> getCars();
    public Car getCar(Long carId);
}
