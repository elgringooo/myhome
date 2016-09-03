package com.myhome.service.impl;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.DataSetTestExecutionListener;
import com.excilys.ebi.spring.dbunit.test.ExpectedDataSet;
import com.myhome.domain.Company;
import com.myhome.service.ICompanyService;

//Le listener � DataSetTestExecutionListener � s'occupe de charger les donn�es avant l'ex�cution de la m�thode de test. 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/conf-test/application-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DataSetTestExecutionListener.class })
@Transactional
public class CompanyServiceImplTest {

	@Autowired
	private ICompanyService companyService;

	@Before
	public void setup() {

	}

	@Test
	@DataSet("/conf-test/data/company.xml")
	public void findAllCompagnies() {

		// Act
		java.util.List<Company> compagnyList = companyService
				.findAllCompanies();
		int actual = compagnyList.size();

		System.out.println(actual);
		// Assert
		final int expected = 3;
		Assert.assertEquals(expected, actual);
	}

	@Test
	@DataSet("/conf-test/data/company.xml")
	@ExpectedDataSet(value="/conf-test/data/expected-company.xml", caseSensitiveTableNames=false)
	public void deleteCompany() {

		Company company = new Company();
		company.setId(1);
		companyService.deleteCompany(company);
	}

}
