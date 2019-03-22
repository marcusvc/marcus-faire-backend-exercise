package com.faire.marcus.exercise.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class InventoryLevel {
	
	private String sku;
	@XmlElement(name="current_quantity")
	private Integer currentQuantity;
	private Boolean discontinued;
	@XmlElement(name="backordered_until")
	private String backorderedUntil;

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Integer getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(Integer currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public Boolean getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}
	public String getBackorderedUntil() {
		return backorderedUntil;
	}
	public void setBackorderedUntil(String backorderedUntil) {
		this.backorderedUntil = backorderedUntil;
	}

}
