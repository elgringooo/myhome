package com.myhome.designpattern.specification.genericspec.sample.genericspec;

import static com.myhome.designpattern.specification.genericspec.sample.domain.Region.SOUTH;
import static com.myhome.designpattern.specification.genericspec.sample.domain.Region.SOUTH_EAST;
import static com.myhome.designpattern.specification.genericspec.sample.domain.Region.SOUTH_WEST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.myhome.designpattern.specification.genericspec.Specification;
import com.myhome.designpattern.specification.genericspec.sample.domain.Car;
import com.myhome.designpattern.specification.genericspec.sample.domain.Color;
import com.myhome.designpattern.specification.genericspec.sample.domain.Region;

/**
 * Default implementation of the {@link CarService} interface.
 */
public class CarServiceImpl implements CarService {

  private CarRepository repository;

  /**
   * {@inheritDoc}
   */
  public void setRepository(final CarRepository repository) {
    this.repository = repository;
  }

public static void main(String[] args) {
	new CarServiceImpl().findCandidateCars();
}




  /**
   * {@inheritDoc}
   */
  public Collection<Car> findCandidateCars() {

    final Collection<Car> cars = new  ArrayList<>();
    
    
    Car c  = new Car();
    c.setColor(Color.GREEN);
    c.setConvertible(true);
    
    cars.add(c);
    final Collection<Car> keepers = new HashSet<Car>();

    final Specification<Car> colorRed = new CarColorSpecification(Color.RED);
    final Specification<Car> convertible = new ConvertibleCarSpecification();
    final Specification<Car> approvedRegion = new CarOwnerRegionSpecification(getAuthorizedRegions());

    final Specification<Car> candidateCarSpecification =
       colorRed.and(approvedRegion.or(convertible));
    
    
   
    for (final Car car : cars) {
    	
    

    	 
      if (candidateCarSpecification.isSatisfiedBy(car))
        keepers.add(car);
    }
    return keepers;
  }

  private Set<Region> getAuthorizedRegions() {
    Set<Region> regions = new HashSet<Region>();
    regions.add(SOUTH_WEST);
    regions.add(SOUTH_EAST);
    regions.add(SOUTH);

    return regions;
  }

  /**
   * Check the national used car repository and find cars satistfying the given specification.
   *
   * @param specification Car specification.
   * @return Candidate cars.
   */
  public Collection<Car> findCandidateCars(Specification<Car> specification) {

    final Collection<Car> keepers = new HashSet<Car>();
    final Collection<Car> cars = repository.findAllCarsInStock();

    for (final Car car : cars) {
      if (specification.isSatisfiedBy(car))
        keepers.add(car);
    }
    return keepers;
  }

}
