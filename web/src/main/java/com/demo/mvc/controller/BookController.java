package com.demo.mvc.controller;

import com.demo.bean.Book;
import com.demo.mvc.common.BaseController;
import com.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by adon on 2016/1/30 0030.
 */
@Controller
@RequestMapping("/book/book.do")
public class BookController extends BaseController {

	@Autowired
	private BookService bookService;

	@RequestMapping(params = "method=add")
	public String add(Book book){
		System.out.println("bookname:"+book.getName());
		System.out.println("author:"+book.getAuthor());
		bookService.add(book);
		return "success";
	}

	@RequestMapping(params = "method=update")
	public String update(Book book) {
		bookService.update(book);
		return "success";
	}

	@RequestMapping(params = "method=saveOrUpdate")
	public String saveOrUpdate(Book book) {
		bookService.saveOrUpdate(book);
		return "success";
	}

	@RequestMapping(params = "method=get")
	public String get(Long id) {
		Book book = bookService.get(id);
		return "success";
	}
}
