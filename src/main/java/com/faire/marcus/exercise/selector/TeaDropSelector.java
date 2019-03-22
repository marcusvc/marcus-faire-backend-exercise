package com.faire.marcus.exercise.selector;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Product;
import com.faire.marcus.exercise.model.Products;

@Component
public class TeaDropSelector implements BrandSelector<Product, Products> {

	@Override
	public List<Product> select(Products products) {
		return products.getProducts().stream()
				.filter(product -> "b_d2481b88".equals(product.getBrandId()))
				.collect(Collectors.toList());
	}

}
