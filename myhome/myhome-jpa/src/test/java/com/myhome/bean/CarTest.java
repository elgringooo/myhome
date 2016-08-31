package com.myhome.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.myhome.dto.Car;

public class CarTest {

	private static Validator validator;

	@BeforeClass
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

	}

	@Test
	public void expirationDate() {
		Car car = new Car("Morris", "DD-AB-123", 4);
	
	 
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		car.setDate(c.getTime());
//		c.add(Calendar.DATE, 3);
		
		Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);

		 
		ConstraintViolation<Car> next = constraintViolations.iterator().next();
		System.out.println(next.getPropertyPath().toString());
		System.out.println(next.getMessage().toString());
		assertEquals(1, constraintViolations.size());
		
		assertEquals("date", next.getPropertyPath().toString());
		
	}

	@Test
	public void manufacturerIsNull() {
		Car car = new Car(null, "DD-AB-123", 4);

		Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);

		assertEquals(1, constraintViolations.size());
		assertEquals("manufacturer", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	@Test
	public void licensePlateTooShort() {
		Car car = new Car("Morris", "D", 4);

		Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
		assertEquals(1, constraintViolations.size());
		assertEquals("licensePlate", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	@Test
	public void seatCountTooLow() {
		Car car = new Car("Morris", "DD-AB-123", 1);

		Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);

		assertEquals(1, constraintViolations.size());
		assertEquals("seatCount", constraintViolations.iterator().next().getPropertyPath().toString());
	}

	@Test
	public void carIsValid() {
		Car car = new Car("Morris", "DD-AB-123", 2);

		Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);

		assertEquals(0, constraintViolations.size());
	}
}