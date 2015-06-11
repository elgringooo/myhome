package com.myhome.mockito;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.myhome.dao.CompanyDao;
import com.myhome.domain.Company;
import com.myhome.service.ICompanyService;
import com.myhome.service.impl.CompanyServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceMockitoTest {

	@InjectMocks
	@Spy
	private ICompanyService companyService = new CompanyServiceImpl();

	@Mock
	private CompanyDao companyDAO;

	@Before
	public void setUpMockito() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveCompany() {

		Company c = new Company();
		c.setId(1);
		c.setEmployees(3);
		c.setName("Massilia");

		companyService.saveCompany(c);
		Mockito.verify(companyDAO).save(c);

	}

	@Test
	public void findAll() {
		
		
		//Preparation
		List<Company> companies = new ArrayList<Company>();
		companies.add(new Company("com1", 1, "heelo1"));
		companies.add(new Company("com2", 2, "heelo2"));
		companies.add(new Company("com3", 3, "heelo3"));
		Mockito.when(companyDAO.findAll()).thenReturn(companies);
		
		
		Assert.assertEquals(companies, companyService.findAllCompanies());
		
		
		
		

	}
}
