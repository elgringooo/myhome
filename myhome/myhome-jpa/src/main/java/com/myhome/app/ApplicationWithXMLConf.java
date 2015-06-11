package com.myhome.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myhome.domain.Car;
import com.myhome.domain.CarItem;
import com.myhome.domain.User;
import com.myhome.service.CarManager;
import com.myhome.service.IUserManager;

public class ApplicationWithXMLConf {
	public static void main(String[] args) {

		new ApplicationWithXMLConf().go();
	}

	public void go() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"conf/application-context.xml");
		oneToMany(ctx);

	}

	public void oneToMany(ApplicationContext ctx) {
		CarManager carManager = (CarManager) ctx.getBean("carManagerImpl");

		Car car = new Car();
		car.setModel("Qashqai");

		List<CarItem> items = new ArrayList<CarItem>();
		for (int i = 0; i < 3; i++) {
			CarItem item = new CarItem();
			item.setName("name" + i);
			item.setDescription("desc" + i);
			item.setCar(car);
			items.add(item);
		}

		car.setItems(items);
		System.out.println("Car : " + car);

		// create
		carManager.saveCar(car);
		System.out.println("Car after create: " + car);

		// update item
		car.setModel("Qashqai2");
		car.getItems().get(0).setName("changed");
		carManager.saveCar(car);
		System.out.println("Car after item 0 update: " + car);
	}

	public void createUser(ApplicationContext ctx) {
		IUserManager userManager = (IUserManager) ctx.getBean("userManagerImpl");
		User user = new User();
		user.setUsername("johndoe");
		user.setName("John Doe");
		userManager.saveUser(user);

		user.setUsername("hello");
		user.setName("noel Doe");
		userManager.saveUser(user);

		List<User> list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());

		System.out.println("Users: " + list);
	}

}