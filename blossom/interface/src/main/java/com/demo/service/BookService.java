package com.demo.service;

import com.demo.bean.Book;

/**
 * Created by adon on 2016/1/31 0031.
 */
public interface BookService {

	public void add(Book book);

	public void update(Book book);

	public void saveOrUpdate(Book book);

	public void remove(Book book);

	public Book get(Long id);

}
