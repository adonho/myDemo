package com.demo.common.util;

import java.util.ArrayList;
import java.util.Collection;

public class PageList
		extends ArrayList {
	protected int pageCount = 0;
	protected int currentPage = 0;
	protected int totalRowCount = 0;

	public PageList() {
	}

	public PageList(Collection c) {
		super(c);
	}

	public PageList(int initialCapacity) {
		super(initialCapacity);
	}

	public void setPageCount(int pageCount) {
		if (pageCount < 0) {
			pageCount = 0;
		}
		this.pageCount = pageCount;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage < 0) {
			currentPage = 0;
		}
		this.currentPage = currentPage;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public int getTotalRowCount() {
		return this.totalRowCount;
	}

	public void calcPageCount(int pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		}
		int n = this.totalRowCount % pageSize;
		if (n == 0) {
			setPageCount(this.totalRowCount / pageSize);
		} else {
			setPageCount((this.totalRowCount - n) / pageSize + 1);
		}
	}
}
