package com.myhome.app.jpasamples;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myhome.config.Config;
import com.myhome.domain.Customer;
import com.myhome.domain.CustomerDetails;
import com.myhome.repository.CustomerRepository;
/**
 * http://www.databaseprimer.com/pages/relationship_1to1/
 * http://blog.paumard.org/cours/jpa/chap03-entite-relation.html
 * @author Thales
 *
 */
public class CustomerSample {

	private Logger logger = org.apache.log4j.Logger
			.getLogger(CustomerSample.class);

	public static void main(String[] args) {
		new CustomerSample().go();
	}

	public void go() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);
		context.close();

	}

	/**
	 * Customer[ID, ....] 1-------->1 CustomerDetails[ID, CUSTOMER_ID]
	 *
	 */
	public void oneToOneBidrectionnal(ApplicationContext ctx) {
		logger.info("oneToOneBidrectionnal");

		CustomerRepository repository = ctx.getBean(CustomerRepository.class);

		Customer customer = new Customer("Jack", "Bauer");
		customer.setEmailAddress("jack.bauer@gmail.com");
		customer.setLogin("jbauer");

		CustomerDetails details = new CustomerDetails();
		details.setAge(40);
		details.setCustomer(customer);
		details.setWeight(90);
		customer = repository.save(customer);

		logger.info("Customer =" + repository.findOne(customer.getId()));

	}

}
