package com.myhome.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.myhome.domain.User;
import com.myhome.domain.UserSearch;

public class UserRESTClient {

	public static final String SERVER_URI = "http://localhost:8181/springmvcsamples";

	public static final String ENDPOINT = SERVER_URI + "/user";

	private RestTemplate restTemplate;

	public UserRESTClient() {
		restTemplate = new RestTemplate();
		System.out.println(testGetUserListJSON());
		System.out.println(testGetList());
		testCreateUser();
		System.out.println(testGetList());
		testUpdateUser();
		System.out.println(testGetList());
		testDeleteUser();
		System.out.println(testGetList());
	}

	public UserSearch testGetUserListJSON() {
		UserSearch result = restTemplate.getForObject(ENDPOINT
				+ "/getUserListXML", UserSearch.class);
		return result;
	}

	public List<User> testGetList() {
		System.out.println("\n******GET LIST ******");
		User[] result = restTemplate.getForObject(ENDPOINT + "/getList",
				User[].class);
		return Arrays.asList(result);
	}

	public void testCreateUser() {
		System.out.println("\n******CREATE******");
		User p = new User();
		p.setId(1);
		p.setName("Gege");
		p.setAge(18);
		restTemplate.postForObject(ENDPOINT + "/create", p, User.class);
	}

	private void testUpdateUser() {
		System.out.println("\n******UPDATE******");
		User p = new User();
		p.setId(1);
		p.setName("Gege");
		p.setAge(25);
		restTemplate.put(ENDPOINT + "/update/1", p, User.class);

	}

	private void testDeleteUser() {
		System.out.println("\n******DELETE******");
		restTemplate.delete(ENDPOINT + "/delete/1");

	}

	public static void main(String[] args) {
		new UserRESTClient();
	}
};