package com.faire.marcus.exercise.selector;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Order;
import com.faire.marcus.exercise.model.Orders;
import com.faire.marcus.exercise.repository.InventoryRepository;

@Component
public class TeaDropBestSellingSelector implements BrandSelector<Order, Orders> {
	
	private InventoryRepository inventoryRepository;
	
	public TeaDropBestSellingSelector(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public List<Order> select(Orders orders) {
		return orders.getOrders().stream()
				.filter((order) -> ("DELIVERED".equals(order.getState()) || "IN_TRANSIT".equals(order.getState()) || "PRE_TRANSIT".equals(order.getState())))
				.filter(order -> order.getItems().stream()
						.allMatch(item -> inventoryRepository.containsKey(item.getProductOptionId())))
				.collect(Collectors.toList());
	}

}
