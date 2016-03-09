package com.demo.dao.impl;

import com.demo.common.dao.BaseDaoHibernate;
import com.demo.bean.Book;
import com.demo.dao.BookDao;
import org.springframework.stereotype.Repository;

/**
 * Created by adon on 2016/1/31 0031.
 */
@Repository(value = "bookDao")
public class BookDaoHibernateImpl extends BaseDaoHibernate<Book> implements BookDao {

	@Override
	public void add(Book book) {
		save(book);
	}

	@Override
	public void update(Book book) {
		saveOrUpdate(book);
	}
}
