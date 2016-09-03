package com.myhome.designpattern.specification.genericspec.sample.genericspec;

import java.util.Set;

import com.myhome.designpattern.specification.genericspec.AbstractSpecification;
import com.myhome.designpattern.specification.genericspec.sample.domain.Car;
import com.myhome.designpattern.specification.genericspec.sample.domain.Region;

/**
 * Specification to tell if a car's owner's home address is in the specifed set of
 * authorized regions.
 */
public class CarOwnerRegionSpecification extends AbstractSpecification<Car> {

  private Set<Region> authorizedRegions;

  /**
   * Set authorized regions.
   * The car's owner's home address must be in one of the supplied regions for the
   * specification to be satisfied.
   *
   * @param authorizedRegions Set of regions.
   */
  public CarOwnerRegionSpecification(Set<Region> authorizedRegions) {
    this.authorizedRegions = authorizedRegions;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isSatisfiedBy(Car car) {
    return authorizedRegions.contains(car.owner().homeAddress().region());
  }


}
