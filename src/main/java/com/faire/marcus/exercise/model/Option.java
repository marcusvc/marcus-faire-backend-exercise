package com.faire.marcus.exercise.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class Option {

	private String id;
	@XmlElement(name="product_id")
	private String productId;
	private Boolean active;
	private String name;
	private String sku;
	@XmlElement(name="available_quantity")
	private Integer availableQuantity;
	@XmlElement(name="backordered_until")
	private String backorderedUntil; //"20190208T000915.000Z",
	@XmlElement(name="created_at")
	private String createdAt; //"20190314T000915.000Z",
	@XmlElement(name="updated_at")
	private String updatedAt; //"20190315T000915.000Z"

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public String getBackorderedUntil() {
		return backorderedUntil;
	}
	public void setBackorderedUntil(String backorderedUntil) {
		this.backorderedUntil = backorderedUntil;
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

}
