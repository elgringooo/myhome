package com.myhome.dao.impl;

import org.springframework.stereotype.Repository;

import com.myhome.dao.UserDao;
import com.myhome.domain.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements
		UserDao {

	public UserDaoImpl() {
	}

}