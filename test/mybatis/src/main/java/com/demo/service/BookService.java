package com.demo.service;

import com.demo.bean.Book;

import java.util.Map;

/**
 * Created by adon on 2016/2/1 0001.
 */
public interface BookService {

	public Book getBook(Long id);

	public Map getBookAsMap(Long id);

	public void addBook(Book book);

	public void remove(Long id);

	public void update(Book book);

}
