package com.myhome.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy(true)
public interface TestDao {

	public String test();
}
