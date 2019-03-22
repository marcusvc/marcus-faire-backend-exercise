package com.faire.marcus.exercise.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class Product {
	
	private String id;
	@XmlElement(name="brand_id")
	private String brandId;
	@XmlElement(name="short_description")
	private String shortDescription;
	@XmlElement(name="wholesale_price_cents")
	private String wholesalePriceCents;
	@XmlElement(name="retail_price_cents")
	private String retailPriceCents;
	private Boolean active;
	private String name;
	@XmlElement(name="unit_multiplier")
	private Integer unitMultiplier;
	private List<Option> options;
	@XmlElement(name="created_at")
	private String createdAt; //"20190314T000915.000Z"
	@XmlElement(name="updated_at")
	private String updatedAt; //"20190315T000915.000Z"

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getWholesalePriceCents() {
		return wholesalePriceCents;
	}
	public void setWholesalePriceCents(String wholesalePriceCents) {
		this.wholesalePriceCents = wholesalePriceCents;
	}
	public String getRetailPriceCents() {
		return retailPriceCents;
	}
	public void setRetailPriceCents(String retailPriceCents) {
		this.retailPriceCents = retailPriceCents;
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
	public Integer getUnitMultiplier() {
		return unitMultiplier;
	}
	public void setUnitMultiplier(Integer unitMultiplier) {
		this.unitMultiplier = unitMultiplier;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
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
	@Override
	public String toString() {
		return "Product [" + (brandId != null ? "brandId=" + brandId + ", " : "") + (name != null ? "name=" + name : "")
				+ "]";
	}
	
}