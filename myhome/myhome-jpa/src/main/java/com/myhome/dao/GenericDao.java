package com.myhome.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<T, PK extends Serializable> {

	public long countAll(final Map<String, Object> params);

	public T create(final T t);

	public void delete(final PK id);

	public T find(final PK id);

	public T update(final T t);

	public List<T> findAll();

	public T saveOrUpdate(final T t);

}
