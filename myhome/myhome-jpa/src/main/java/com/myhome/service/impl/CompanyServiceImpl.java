package com.myhome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.dao.CompanyDao;
import com.myhome.domain.Company;
import com.myhome.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private CompanyDao companyDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Company> findAllCompanies() {
		return companyDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Company findCompanyById(long id) {
		return companyDAO.find(id);
	}

	@Override
	@Transactional
	public void saveCompany(Company company) {
		companyDAO.save(company);

	}

	@Override
	@Transactional
	public void deleteCompany(Company company) {
		companyDAO.remove(company);

	}

	@Override
	@Transactional
	public void updateCompany(Company company) {
		companyDAO.update(company);

	}
}
