package com.demo.common.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

/**
 * Created by adon on 2016/2/20 0020.
 */
public class MMySQLDialect
		extends MySQLDialect {
	public MMySQLDialect() {
		registerHibernateType(-16, Hibernate.TEXT.getName());

		registerHibernateType(-1, Hibernate.STRING.getName());

		registerHibernateType(3, Hibernate.BIG_DECIMAL.getName());
	}
}
