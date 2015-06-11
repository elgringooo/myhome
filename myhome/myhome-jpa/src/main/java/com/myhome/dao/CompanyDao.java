package com.myhome.dao;

import java.util.List;

import com.myhome.domain.Company;

public interface CompanyDao {
	public List<Company> findAll();

	void save(final Company company);

	void remove(final Company company);

	void update(final Company company);

	Company find(final long id);
}
