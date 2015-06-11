package com.myhome.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.myhome.service.ISimpleService;

//pas besoin de declarer une location , par default spring recherche dans
//\com\myhome\service\SimpleServiceTest-context.xml

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class SimpleServiceTest {

	@Autowired
	private ISimpleService simpleService;

	@Test
	public void testAdd() {
		// Arrange
		final int param1 = 2;
		final int param2 = 2;
		final int expected = 4;

		// Act
		final int resultat = simpleService.add(param1, param2);

		// Assert
		Assert.assertEquals(expected, resultat);
	}

}
