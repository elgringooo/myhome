package com.myhome.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.myhome.dao.TestDao;

@Lazy(true)
@Component(value = "test2DAO")
public class Test2DaoImpl implements TestDao {

	public Test2DaoImpl() {
		System.out.println("CREATION DE Test2DAO");
	}

	@Override
	public String test() {
		return "test FROM Test2DAO";
	}
}
