package com.myhome.service;

import java.util.List;

import com.myhome.domain.Company;

public interface ICompanyService {
	public List<Company> findAllCompanies();

	public Company findCompanyById(long id);

	public void saveCompany(Company company);

	public void deleteCompany(Company company);

	public void updateCompany(Company company);

}
