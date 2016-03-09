package com.demo.common.dao;

import java.io.Serializable;

/**
 * Created by adon on 2016/1/31 0031.
 */
public interface Dao<T> {

	public void save(T t);

	public void saveOrUpdate(T t);

	public void remove(T t);

	public T get(Serializable id);

}
