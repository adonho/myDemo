package com.demo.common.dao;

import com.demo.common.util.hibernate.HibernateTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by adon on 2016/1/31 0031.
 */
public abstract class BaseDaoHibernate<T> implements Dao<T>{

	protected final Logger log = Logger.getLogger(this.getClass());

	private Class<T> clz = (Class<T>) ((ParameterizedType) this.getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	public void save(T t) {
		hibernateTemplate.save(t);
	}

	public void saveOrUpdateAll(T... t) {
		hibernateTemplate.saveOrUpdateAll(t);
	}

	public void saveOrUpdate(T t) {
		hibernateTemplate.saveOrUpdate(t);
	}

	public void remove(T t) {
		hibernateTemplate.delete(t);
	}

	public T get(Serializable id) {
		return hibernateTemplate.get(clz, id);
	}
}
