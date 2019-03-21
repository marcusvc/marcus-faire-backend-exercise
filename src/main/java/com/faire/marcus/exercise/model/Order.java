package com.faire.marcus.exercise.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType (XmlAccessType.FIELD)
public class Order {
	
	private Integer id;
	private List<OrderItem> items = new ArrayList<>();

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<OrderItem> getItems() {
		return new ArrayList<>(items);
	}
	public void setItems(List<OrderItem> items) {
		this.items = new ArrayList<>(items);
	}
	
}
