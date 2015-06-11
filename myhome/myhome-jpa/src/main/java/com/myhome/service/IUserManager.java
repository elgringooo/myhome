package com.myhome.service;

import java.util.List;

import com.myhome.domain.User;

public interface IUserManager {

	void saveUser(User user);

	List<User> findAllUsers();

	public User findUser(int id);

}