package com.myhome.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.myhome.dao.TestDao;

@Lazy(true)
@Component(value = "testDAO")
public class TestDaoImpl implements TestDao {

	public TestDaoImpl() {
	}

	@Override
	public String test() {
		return "test FROM TestDAO";
	}
}
