package com.demo.service.impl;

import com.demo.bean.Book;
import com.demo.dao.BookDao;
import com.demo.common.service.BaseService;
import com.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by adon on 2016/1/31 0031.
 */
@Repository(value = "bookServiceLocal")
public class BookServiceImpl extends BaseService implements BookService {

	@Autowired
	private BookDao bookDao;

	@Transactional
	public void add(Book book) {
		this.bookDao.add(book);
	}

	@Transactional
	public void update(Book book) {
		this.bookDao.update(book);
	}

	@Transactional
	public void saveOrUpdate(Book book) {
		this.bookDao.saveOrUpdate(book);
	}

	@Transactional
	public void remove(Book book) {
		this.bookDao.remove(book);
	}

	public Book get(Long id) {
		return this.bookDao.get(id);
	}
}
