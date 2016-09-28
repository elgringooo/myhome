package com.myhome.customvalidator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class HibernateValidatorMain {
	public static void main(String[] args) throws Exception {
		try {
			Car ca = new Car("dede", "l", 3);
			ca.setAge("100");

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Car>> constraintViolations =

					validator.validate(ca);

			if (constraintViolations.size() > 0) {
				System.out.println("Impossible de valider les donnees du bean : ");
				for (ConstraintViolation<Car> contrainte : constraintViolations) {
					String severite = null;
					for (Class<? extends Payload> gravite : contrainte.getConstraintDescriptor().getPayload()) {
						severite = gravite.getName();

						break;
					}

					System.out.println(severite + " " +contrainte.getMessage());
					;

					try {
						if (severite != null) {
							Object o = Class.forName(severite).newInstance();
							
							if (o instanceof Exception) {
								throw (Exception) o;
							}
						}
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println(severite + "\t " + contrainte.getRootBeanClass().getSimpleName() + "."
							+ contrainte.getPropertyPath() + " " + contrainte.getMessage());
				}
			} else {
				System.out.println("Les donnees du bean sont validees");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
