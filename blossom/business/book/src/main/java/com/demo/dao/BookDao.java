package com.demo.dao;

import com.demo.common.dao.Dao;
import com.demo.bean.Book;

/**
 * Created by adon on 2016/1/31 0031.
 */
public interface BookDao extends Dao<Book> {

	//模拟数据库操作
	void add(Book book);

	void update(Book book);
}
