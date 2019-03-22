package com.faire.marcus.exercise.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Products {
	
	private Integer page;
	private Integer limit;
	private List<Product> products = new ArrayList<>();

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public List<Product> getProducts() {
		return new ArrayList<>(products);
	}
	public void setProducts(List<Product> products) {
		this.products = new ArrayList<>(products);
	}

}
