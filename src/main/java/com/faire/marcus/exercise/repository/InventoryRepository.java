package com.faire.marcus.exercise.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Option;
import com.faire.marcus.exercise.model.Product;

@Component
public class InventoryRepository {
	
	private HashMap<String, Integer> inventory = new HashMap<>();
	
	public void add(List<Product> products) {
		products.forEach(product -> product.getOptions().forEach(this::add));
	}
	
	public void add(Option option) {
		if (option.getAvailableQuantity() != null) {
			inventory.merge(option.getId(), option.getAvailableQuantity(), Integer::sum);
		}
	}
	
	public Integer getQuantity(Option option) {
		return inventory.getOrDefault(option.getId(), 0);
	}
	
	public Integer getQuantity(String optionId) {
		return inventory.getOrDefault(optionId, 0);
	}
	
	public HashMap<String, Integer> getAll() {
		return new HashMap<>(inventory);
	}
	
	public boolean isEmpty() {
		return inventory.isEmpty();
	}

}
