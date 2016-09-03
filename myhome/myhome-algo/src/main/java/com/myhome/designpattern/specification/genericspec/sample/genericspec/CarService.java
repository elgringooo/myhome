package com.myhome.designpattern.specification.genericspec.sample.genericspec;

import java.util.Collection;

import com.myhome.designpattern.specification.genericspec.Specification;
import com.myhome.designpattern.specification.genericspec.sample.domain.Car;

/**
 * Service for dealing with cars.
 */
public interface CarService {

  /**
   * Set a repository to use for car lookups.
   *
   * @param repository Car repository to use
   */
  void setRepository(CarRepository repository);

  /**
   * Find candidate cars. A candidate car is a car that is
   * of the color red and is a convertible,
   * <i>or</i>
   * is red and has an owner that lives in an approved region and is less than five years old.
   *
   * @return Cars in the configured repository matching the description above.
   */
  Collection<Car> findCandidateCars();

  /**
   * Find cars from the configured repository satisfying the given specification.
   *
   * @param specification Specification to use when finding cars.
   */
  Collection<Car> findCandidateCars(Specification<Car> specification);
}
