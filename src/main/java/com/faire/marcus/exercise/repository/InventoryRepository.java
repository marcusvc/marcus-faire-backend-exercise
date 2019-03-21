package com.faire.marcus.exercise.repository;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Product;
import com.faire.marcus.exercise.model.Products;

@Component
public class InventoryRepository {
	
	private HashMap<Product, Integer> inventory = new HashMap<>();
	
	public void add(Products products) {
		products.getProducts().forEach(this::add);
	}
	
	public void add(Product product) {
		inventory.merge(product, 1, Integer::sum);
		//inventory.merge(product, 1, (a, b) -> a + b);
	}
	
	public Integer getQuantity(Product product) {
		return inventory.getOrDefault(product, 0);
	}

}
