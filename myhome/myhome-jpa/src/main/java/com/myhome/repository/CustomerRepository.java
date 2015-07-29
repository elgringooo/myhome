package com.myhome.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myhome.domain.Customer;

/**
 * The Interface CustomerRepository. SPRING-DATA
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>,
		CustomerRepositoryCustom {

	/**
	 * Convention de nommage =>
	 * http://docs.spring.io/spring-data/data-jpa/docs/1.0
	 * .0.RC1/reference/html/#repositories.query-methods.property-expressions
	 * 
	 */

	/**
	 * Test semantique spring data
	 * 
	 * @param lastName
	 * @return
	 */
	List<Customer> findByLastNameLikeOrderByLastNameAsc(String lastName);

	/**
	 * Test Pageable
	 * 
	 * @param lastName
	 * @param pageable
	 * @return
	 */
	Page<Customer> findByLastName(String lastName, Pageable pageable);
	
	List<Customer> findByLastName(String lastName);

	/**
	 * Test deux filtres
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

	/**
	 * Using @Query
	 * 
	 * @param emailAddress
	 * @return
	 */
	@Query("select u from Customer u where u.emailAddress = ?1")
	Customer findByEmailAddress(String emailAddress);

	/**
	 * Using named parameters
	 */
	@Query("select u from Customer u where u.firstName = :firstname or u.lastName = :lastname")
	Customer findByNamedParameter(@Param("lastname") String lastname,
			@Param("firstname") String firstname);

}
