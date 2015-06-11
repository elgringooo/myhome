package com.myhome.service.impl;

import com.myhome.service.ISimpleService;

public class SimpleServiceImpl implements ISimpleService {

	@Override
	public int add(int a, int b) {
		return a + b;
	}

}
