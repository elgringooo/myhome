package com.myhome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.UserDao;
import com.myhome.domain.User;
import com.myhome.service.IUserManager;

@Service
public class UserManagerImpl implements IUserManager {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = false)
	public void saveUser(User user) {
		userDao.saveOrUpdate(user);
	}

	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		return userDao.findAll();
	}

	@Transactional(readOnly = true)
	public User findUser(int id) {
		return userDao.find(id);
	}

}