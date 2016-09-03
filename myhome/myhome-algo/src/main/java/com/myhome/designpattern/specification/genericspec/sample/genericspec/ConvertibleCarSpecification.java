package com.myhome.designpattern.specification.genericspec.sample.genericspec;

import com.myhome.designpattern.specification.genericspec.AbstractSpecification;
import com.myhome.designpattern.specification.genericspec.sample.domain.Car;

/**
 * Specification to tell if a car is a convertible.
 */
public class ConvertibleCarSpecification extends AbstractSpecification<Car> {

  /**
   * {@inheritDoc}
   */
  public boolean isSatisfiedBy(Car car) {
    return car.isConvertible();
  }


}
