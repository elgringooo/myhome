package com.myhome.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.myhome.dao.GenericDao;
import com.myhome.domain.IModel;

public abstract class GenericDaoImpl<T extends IModel<PK>, PK extends Serializable>
		implements GenericDao<T, PK> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> type;

	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public long countAll(final Map<String, Object> params) {

		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");

		queryString.append(type.getSimpleName()).append(" o ");
		// queryString.append(this.getQueryClauses(params, null));

		final Query query = this.entityManager.createQuery(queryString
				.toString());

		return (Long) query.getSingleResult();

	}

	public T create(final T t) {
		this.entityManager.persist(t);
		return t;
	}

	public T saveOrUpdate(T t) {
		if (t.getId() == null) {
			entityManager.persist(t);
		} else {
			if (!entityManager.contains(t)) {
				entityManager.merge(t);
			}
		}
		entityManager.flush();
		return t;
	}

	public void delete(final PK id) {
		this.entityManager.remove(this.entityManager.getReference(type, id));
	}

	public T find(final PK id) {
		return (T) this.entityManager.find(type, id);
	}

	public T update(final T t) {
		return this.entityManager.merge(t);
	}

	public List<T> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(type);
		Root<T> root = cq.from(type);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}