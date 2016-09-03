package com.myhome.designpattern.specification.genericspec.sample.genericspec;

import java.util.Collection;

import com.myhome.designpattern.specification.genericspec.sample.domain.Car;

/**
 * Repository for cars.
 */
public interface CarRepository {

  /**
   * Return all cars in the repository.
   *
   * @return All cars in the repository.
   */
  public Collection<Car> findAllCarsInStock();
}