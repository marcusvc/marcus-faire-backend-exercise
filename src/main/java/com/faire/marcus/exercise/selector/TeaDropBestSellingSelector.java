package com.faire.marcus.exercise.selector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Item;
import com.faire.marcus.exercise.model.Orders;
import com.faire.marcus.exercise.repository.InventoryRepository;

@Component
public class TeaDropBestSellingSelector implements Selector<Item, Orders> {
	
	private InventoryRepository inventoryRepository;
	
	public TeaDropBestSellingSelector(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public List<Item> select(Orders orders) {
		List<Item> result = new ArrayList<>();
		orders.getOrders().stream()
		.filter(order -> "DELIVERED".equals(order.getState()) || "IN_TRANSIT".equals(order.getState()) || "PRE_TRANSIT".equals(order.getState()))
		.forEach(order -> order.getItems().stream()
				.filter(item -> inventoryRepository.containsKey(item.getProductOptionId()))
				.forEach(result::add)
			);
		return result;
	}

}
