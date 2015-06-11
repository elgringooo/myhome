package com.myhome.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.myhome.domain.Customer;
import com.myhome.service.ICompanyService;

@RunWith(MockitoJUnitRunner.class)
public class SimpleServiceMockitoTest {

	@Mock
	Customer user;

	// equivalent à Customer customer = Mockito.mock(Customer.class);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void sample1() {
		System.out.println("Sample 1: mock");
		// Example 1 ) Mockito encapsule et contrôle tous les appels effectués
		// sur l’objet Customer.
		System.out.println(user.getLogin()); // affiche null
		user.setLogin("bob");
		System.out.println(user.getLogin()); // affiche encore null !

	}

	@Test
	public void sample2() {
		// Exemple2
		System.out.println("Sample 2: mock");
		Mockito.when(user.getLogin()).thenReturn("bob").thenReturn("gege")
				.thenReturn("isis");
		System.out.println(user.getLogin()); // affiche "bob"
		user.setLogin("drake");
		System.out.println(user.getLogin()); // affiche "gege"
		System.out.println(user.getLogin()); // affiche "isis"

	}

	@Test
	public void sample3() {
		System.out.println("Sample 3: mock");
		// Exemple 3) retablir le comportement de la methode

		// on rétablit le getLogin()
		Mockito.when(user.getLogin()).thenCallRealMethod();

		// affiche null mais cette fois car la variable login vaut vraiment null
		// dans le mock
		System.out.println(user.getLogin());

		// on rétablit également le comportement de la méthode setLogin()
		Mockito.doCallRealMethod().when(user).setLogin(Mockito.anyString());

		user.setLogin("drake");
		System.out.println(user.getLogin()); // affiche enfin "drake" !

		// on rétablit également le comportement de la méthode setLogin()
		Mockito.doCallRealMethod().when(user).setLogin(Mockito.anyString());

		user.setLogin("drake1");
		System.out.println(user.getLogin()); // affiche enfin "drake" !

	}

	@Test
	public void sample4() {

		System.out.println("Sample 4/ leve d exception");
		Mockito.when(user.getLogin()).thenThrow(
				new RuntimeException("PAs cool"));
		try {
			System.out.println(user.getLogin());
			Assert.fail();
		} catch (RuntimeException e) {

		}

	}

	@Test
	public void sample5() {

		// Spy permet d’instancier l’objet mocké, ce qui peut être très utile
		// quand nous souhaitons mocker une classe et non pas une interface.
		// Les méthodes réelles seront appelées si elles ne ont pas redefinies

		System.out.println("Sample 5: spy");
		Customer test = new Customer();
		test.setLogin("gege");
		Customer user = Mockito.spy(test);

		System.out.println(user.getLogin());

		Mockito.doReturn("drake").when(user).getLogin();

		user.setLogin("bob");

		// affiche "drake" mais la vraie valeur dans la classe Customer vaut
		// bien
		// "bob"
		System.out.println(user.getLogin());

	}

}
