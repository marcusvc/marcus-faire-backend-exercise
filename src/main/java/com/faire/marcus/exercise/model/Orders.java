package com.faire.marcus.exercise.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Orders {
	
	private Integer page;
	private Integer limit;
	private List<Order> orders = new ArrayList<>();

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
	public List<Order> getOrders() {
		return new ArrayList<>(orders);
	}
	public void setOrders(List<Order> orders) {
		this.orders = new ArrayList<>(orders);
	}
	
}
