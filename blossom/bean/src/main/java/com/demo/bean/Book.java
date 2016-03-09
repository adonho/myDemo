package com.demo.bean;

import com.demo.common.bean.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by adon on 2016/1/31 0031.
 */
public class Book extends BaseEntity {


	private Long id;
	private String name;
	private String author;
	private BigDecimal price;

	public Book(){}
	public Book(Long id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Book[id=")
				.append(this.id)
				.append(", name=")
				.append(this.name)
				.append(", author=")
				.append(this.author)
				.append(", price=")
				.append(this.price)
				.append("]");

		return sb.toString();
	}
}
