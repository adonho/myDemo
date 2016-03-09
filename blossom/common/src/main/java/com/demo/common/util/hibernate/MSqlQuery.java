package com.demo.common.util.hibernate;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adon on 2016/3/6 0006.
 */
public class MSqlQuery {

	private StringBuilder sql = new StringBuilder(128);

	private List<String> scalars = new LinkedList<String>();

	private List<Object> params = new LinkedList<Object>();

	private Integer page = 1;

	private Integer rowsPerPage = -1;

	private boolean countAllRow = true;

	public MSqlQuery(){}

	public MSqlQuery(String sql){
		this.sql.append(sql);
	}

	public MSqlQuery(Integer page, Integer rowsPerPage){
		this.page = page;
		this.rowsPerPage = rowsPerPage;
	}

	public MSqlQuery(Integer page, Integer rowsPerPage, boolean countAllRow){
		this.page = page;
		this.rowsPerPage = rowsPerPage;
		this.countAllRow = countAllRow;
	}

	public MSqlQuery(String sql, Integer page, Integer rowsPerPage){
		this.sql.append(sql);
		this.page = page;
		this.rowsPerPage = rowsPerPage;
	}

	public MSqlQuery(String sql, Integer page, Integer rowsPerPage, boolean countAllRow){
		this.sql.append(sql);
		this.page = page;
		this.rowsPerPage = rowsPerPage;
		this.countAllRow = countAllRow;
	}

	public MSqlQuery addScalar(String scalar){
		this.scalars.add(scalar);
		return this;
	}

	public MSqlQuery addAllScalars(Collection col){
		this.scalars.addAll(col);
		return this;
	}

	public MSqlQuery addAllScalars(String[] arr){
		for(String s : arr){
			this.scalars.add(s);
		}
		return this;
	}

	public MSqlQuery addParam(Object param){
		this.params.add(param);
		return this;
	}

	public MSqlQuery addAllParams(Collection col){
		this.params.addAll(col);
		return this;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public boolean isCountAllRow() {
		return this.rowsPerPage > 0 && countAllRow;
	}

	public void setCountAllRow(boolean countAllRow) {
		this.countAllRow = countAllRow;
	}

	public String getSql() {
		return sql.toString();
	}

	public void setSql(String sql) {
		this.sql.delete(0, this.sql.length());
		this.sql.append(sql);
	}

	public List<String> getScalars() {
		return scalars;
	}

	public void setScalars(List<String> scalars) {
		this.scalars.clear();
		addAllScalars(scalars);
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params.clear();
		addAllParams(params);
	}

	public MSqlQuery append(CharSequence s) {
		this.sql.append(s);
		return this;
	}
}
