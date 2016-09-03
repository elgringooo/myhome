package com.myhome.designpattern.specification.genericspec.sample.genericspec;

import com.myhome.designpattern.specification.genericspec.AbstractSpecification;
import com.myhome.designpattern.specification.genericspec.sample.domain.Car;
import com.myhome.designpattern.specification.genericspec.sample.domain.Color;

/**
 * Specification to tell if a car is of specified color.
 */
public class CarColorSpecification extends AbstractSpecification<Car> {

  private Color color;

  /**
   * Set color.
   * The car's color must be the same as the set color for the specification to be satisfied.
   *
   * @param color Car color
   */
  public CarColorSpecification(Color color) {
    this.color = color;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isSatisfiedBy(final Car car) {
    return car.color() == color;
  }


}
