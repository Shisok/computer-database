package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

public class Page<E> {

	private List<E> contentPage;

	private int pageInt;
	private int pageInitial;

	public Page() {
		this.pageInt = 0;
		this.pageInitial = 0;
		this.contentPage = new ArrayList<E>();
	}

	public List<E> getContentPage() {
		return contentPage;
	}

	public void setContentPage(List<E> contentPage) {
		this.contentPage = contentPage;
	}

	public int getPageInt() {
		return pageInt;
	}

	public void setPageInt(int page) {
		this.pageInt = page;
	}

	public int getPageInitial() {
		return pageInitial;
	}

	public void setPageInitial(int pageInitial) {
		this.pageInitial = pageInitial;
	}
}
