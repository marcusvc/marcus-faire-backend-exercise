package com.faire.marcus.exercise.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType (XmlAccessType.FIELD)
public class Order {

	private String id;
	@XmlElement(name="created_at")
	private String createdAt;
	@XmlElement(name="updated_at")
	private String updatedAt;
	private String state;
	private List<Item> items = new ArrayList<>();
	private List<Shipment> shipments = new ArrayList<>();
	private Address address;
	@XmlTransient
	private BigDecimal total;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Item> getItems() {
		return new ArrayList<>(items);
	}
	public void setItems(List<Item> items) {
		this.items = new ArrayList<>(items);
	}
	public List<Shipment> getShipments() {
		return new ArrayList<>(shipments);
	}
	public void setShipments(List<Shipment> shipments) {
		this.shipments = new ArrayList<>(shipments);
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
