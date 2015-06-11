package com.myhome.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.myhome.dao.CompanyDao;
import com.myhome.domain.Company;

@Repository
public class CompanyDAOImpl implements CompanyDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Company> findAll() {

		final CriteriaBuilder lCriteriaBuilder = entityManager
				.getCriteriaBuilder();

		final CriteriaQuery<Company> lCriteriaQuery = lCriteriaBuilder
				.createQuery(Company.class);
		final Root<Company> lRoot = lCriteriaQuery.from(Company.class);
		lCriteriaQuery.select(lRoot);
		final TypedQuery<Company> lTypedQuery = entityManager
				.createQuery(lCriteriaQuery);

		return lTypedQuery.getResultList();
	}

	@Override
	public void save(Company company) {
		if (company.getId() == 0) {
			entityManager.persist(company);
		} else {
			entityManager.merge(company);
		}

	}

	@Override
	public void remove(Company company) {
		final Company lCompagny = entityManager.getReference(Company.class,
				company.getId());
		entityManager.remove(lCompagny);

	}

	@Override
	public void update(Company company) {
		final CriteriaBuilder lCriteriaBuilder = entityManager
				.getCriteriaBuilder();

		final CriteriaUpdate<Company> lCriteriaUpdate = lCriteriaBuilder
				.createCriteriaUpdate(Company.class);
		final Root<Company> lRoot = lCriteriaUpdate.from(Company.class);
		final Path<Company> lPath = lRoot.get("id");
		final Expression<Boolean> lExpression = lCriteriaBuilder.equal(lPath,
				company.getId());
		lCriteriaUpdate.where(lExpression);
		lCriteriaUpdate.set("employees", company.getEmployees());
		final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
		final int lRowCount = lQuery.executeUpdate();

		if (lRowCount != 1) {
			final org.hibernate.Query lHQuery = lQuery
					.unwrap(org.hibernate.Query.class);
			final String lSql = lHQuery.getQueryString();
			throw new RuntimeException("Nombre d'occurences (" + lRowCount
					+ ") modifiés différent de 1 pour " + lSql);
		}
	}

	@Override
	public Company find(long id) {
		final Company lCompagny = entityManager.find(Company.class, id);
		return lCompagny;

	}

}
