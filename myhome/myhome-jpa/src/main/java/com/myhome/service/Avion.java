package com.myhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.myhome.dao.TestDao;

@Configuration
public class Avion {

	@Autowired
	@Qualifier("testDAO")
	private TestDao testDAO;

	// @Autowired
	// private List<ITestDAO> lst;

	public Avion() {
	}

	public TestDao getTestDAO() {
		return testDAO;
	}

	public String test() {
		String str = "testDAO = null";
		if (testDAO != null) {
			str = testDAO.test();
		}

		// System.out.println("LIST = " + lst);

		return "THIS IS AVION with dao " + str;
	}
}
