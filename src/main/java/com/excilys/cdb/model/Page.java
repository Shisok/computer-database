package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

public class Page<E> {

	private List<E> contentPage;

	private int pageInt;
	private int pageInitial;
	private int objetPerPage;
	private int indexDebut;
	private int indexFin;
	private String orderAttribute;
	private String orderSort;

	public Page() {
		this.pageInt = 0;
		this.pageInitial = 0;
		this.contentPage = new ArrayList<E>();
		this.objetPerPage = 10;
		this.indexDebut = 0;
		this.indexFin = 0;
		this.orderAttribute = "id";
		this.orderSort = "asc";
	}

	public String getOrderAttribute() {
		return orderAttribute;
	}

	public void setOrderAttribute(String orderAttribute) {
		this.orderAttribute = orderAttribute;
	}

	public String getOrderSort() {
		return orderSort;
	}

	public void setOrderSort(String orderSort) {
		this.orderSort = orderSort;
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

	public int getObjetPerPage() {
		return objetPerPage;
	}

	public void setObjetPerPage(int objetPerPage) {
		this.objetPerPage = objetPerPage;
	}

	public void setIndex(int max) {

		if (max > 5) {

			if (pageInt <= 3) {
				indexDebut = 1;
				indexFin = 5;
			} else if (pageInt > max - 3) {
				indexDebut = max - 4;
				indexFin = max;
			} else {
				indexDebut = pageInt - 2;
				indexFin = pageInt + 2;
			}
		} else {
			indexDebut = 1;
			indexFin = max;
		}
	}

	public int getIndexDebut() {
		return indexDebut;
	}

	public void setIndexDebut(int indexDebut) {
		this.indexDebut = indexDebut;
	}

	public int getIndexFin() {
		return indexFin;
	}

	public void setIndexFin(int indexFin) {
		this.indexFin = indexFin;
	}
}
