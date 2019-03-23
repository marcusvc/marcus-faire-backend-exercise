package com.faire.marcus.exercise.selector;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Item;
import com.faire.marcus.exercise.model.Order;
import com.faire.marcus.exercise.model.Orders;
import com.faire.marcus.exercise.repository.InventoryRepository;
import com.faire.marcus.exercise.util.PriceCentsUtils;

@Component
public class TeaDropMoreExpensiveOrderSelector implements Selector<Order, Orders> {
	
	private InventoryRepository inventoryRepository;
	
	public TeaDropMoreExpensiveOrderSelector(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public List<Order> select(Orders orders) {
		Map<String, BigDecimal> hash = new HashMap<>();
		orders.getOrders().stream()
		.filter(order -> "DELIVERED".equals(order.getState()) || "IN_TRANSIT".equals(order.getState()) || "PRE_TRANSIT".equals(order.getState()))
		.forEach(order -> order.getItems().stream()
				.filter(item -> inventoryRepository.containsKey(item.getProductOptionId()))
				.forEach(item -> hash.merge(item.getOrderId(), calculateItemValue(item), this::addBigDecimal))
			);
		
		Map<String, BigDecimal> sortedHash = hash.entrySet().stream()
				.sorted(Entry.<String, BigDecimal>comparingByValue().reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		
		return orders.getOrders().stream()
			.filter(order -> order.getId().equals(sortedHash.entrySet().stream().findFirst().get().getKey()))
			.peek(order -> order.setTotal(sortedHash.get(order.getId())))
			.collect(Collectors.toList());
					
	}
	
	private BigDecimal calculateItemValue(Item item) {
		return PriceCentsUtils.toBigDecimal(item.getPriceCents()).multiply(new BigDecimal(item.getQuantity()));
	}
	
	private BigDecimal addBigDecimal(BigDecimal bd1, BigDecimal bd2) {
		return bd1.add(bd2);
	}
	
}
