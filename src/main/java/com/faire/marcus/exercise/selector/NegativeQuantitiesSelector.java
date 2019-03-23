package com.faire.marcus.exercise.selector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Option;
import com.faire.marcus.exercise.model.Products;

@Component
public class NegativeQuantitiesSelector implements Selector<Option, Products> {
	
	@Override
	public List<Option> select(Products products) {
		List<Option> result = new ArrayList<>();
		products.getProducts().stream()
				.forEach(product -> product.getOptions().stream()
						.filter(option -> option.getAvailableQuantity()!= null && option.getAvailableQuantity() < 0)
						.forEach(result::add)
				);
		return result;
	}

}
