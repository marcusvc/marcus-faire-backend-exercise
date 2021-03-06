package com.faire.marcus.exercise.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.client.FaireClient;
import com.faire.marcus.exercise.model.Address;
import com.faire.marcus.exercise.model.InventoryLevel;
import com.faire.marcus.exercise.model.Item;
import com.faire.marcus.exercise.model.Option;
import com.faire.marcus.exercise.model.Order;
import com.faire.marcus.exercise.model.Orders;
import com.faire.marcus.exercise.model.Product;
import com.faire.marcus.exercise.model.Products;
import com.faire.marcus.exercise.repository.InventoryRepository;
import com.faire.marcus.exercise.util.ZoneDateTimeUtils;

@Component
public class MetricsController {

	private static final Logger LOG = LoggerFactory.getLogger(MetricsController.class);

	private InventoryRepository inventoryRepository;
	private FaireClient faireClient;
	private SelectorController selectorController;

	public MetricsController(InventoryRepository inventoryRepository, FaireClient faireClient, SelectorController selectorController) {
		this.inventoryRepository = inventoryRepository;
		this.faireClient = faireClient;
		this.selectorController = selectorController;
	}

	public void execute() {
		Products allProducts = faireClient.consumesAllProducts();
		List<Product> teaDropProducts = selectorController.getTeaDropSelector().select(allProducts);
		LOG.debug("Tea Drop products: {}", teaDropProducts);
		inventoryRepository.add(teaDropProducts);
		LOG.debug("Inventory: {}", inventoryRepository.getAll());
		Orders allOrders = faireClient.consumesAllOrders();
		LOG.debug("All orders: {}", allOrders.getOrders());
		List<Order> backorderedOrders = selectorController.getBackorderedSelector().select(allOrders);
		LOG.debug("Backordered: {}", backorderedOrders);
		updateBackorderedOrders(backorderedOrders);
		List<Order> processableOrders = selectorController.getProcessableSelector().select(allOrders);
		LOG.debug("Processable Orders: {}", processableOrders);
		updateProcessableOrders(processableOrders);
		updateProductOption(processableOrders);
		//updateNegativeQauntitiesOptions(allProducts);
		showBestSelling(allOrders);
		showTheLargestOrderByDollarAmount(allOrders);
		showStateWithTheMostOrders(allOrders);
		showStateWithTheMostCanceledOrders(allOrders);
	}

	private void showStateWithTheMostCanceledOrders(Orders orders) {
		List<Address> selecteds = selectorController.getAddressCanceledSelector().select(orders);
		Map<String, Integer> stateOrdersCounterMap = new HashMap<String, Integer>();
		selecteds.stream().forEach(address -> stateOrdersCounterMap.merge(address.getState(), 1, Integer::sum));

		Map<String, Integer> sortedStateOrdersCounterMap = stateOrdersCounterMap.entrySet().stream()
				.sorted(Entry.<String, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		sortedStateOrdersCounterMap.entrySet().stream().findFirst()
		.ifPresent(entry -> LOG.debug("State with the most canceled orders: {} - Orders: {}", entry.getKey(), entry.getValue()));
	}

	private void showStateWithTheMostOrders(Orders orders) {
		List<Address> selecteds = selectorController.getAddressSelector().select(orders);
		Map<String, Integer> stateOrdersCounterMap = new HashMap<String, Integer>();
		selecteds.stream().forEach(address -> stateOrdersCounterMap.merge(address.getState(), 1, Integer::sum));

		Map<String, Integer> sortedStateOrdersCounterMap = stateOrdersCounterMap.entrySet().stream()
				.sorted(Entry.<String, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		sortedStateOrdersCounterMap.entrySet().stream().findFirst()
		.ifPresent(entry -> LOG.debug("State with the most orders: {} - Orders: {}", entry.getKey(), entry.getValue()));
	}

	private void showTheLargestOrderByDollarAmount(Orders orders) {
		List<Order> selecteds = selectorController.getTeaDropMoreExpensiveOrderSelector().select(orders);
		LOG.debug("Largest Order By Dollar Amount:");
		selecteds.stream().findFirst().ifPresent(order -> LOG.debug("Order ID: {} - Order total value: {}",
				order.getId(), order.getTotal()));
	}

	private void showBestSelling(Orders orders) {
		List<Item> selecteds = selectorController.getTeaDropBestSellingSelector().select(orders);
		Map<String, Integer> calculated = new HashMap<>();
		selecteds.stream().forEach(item -> calculated.merge(item.getId(), item.getQuantity(), Integer::sum));
		Map<String, Integer> sortedMap = calculated.entrySet().stream()
				.sorted(Entry.<String, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		LOG.debug("Best Selling:");
		sortedMap.entrySet().stream().forEach(entry -> LOG.debug("Option ID: {} - Selling: {}", entry.getKey(), entry.getValue()));
	}

	private void updateNegativeQauntitiesOptions(Products products) {
		List<Option> selecteds = selectorController.getNegativeQuantitiesSelector().select(products);
		selecteds.stream().forEach(option -> {
			InventoryLevel inventoryLevel = new InventoryLevel();
			inventoryLevel.setSku(option.getSku());
			inventoryLevel.setCurrentQuantity(option.getAvailableQuantity() * -1);
			inventoryLevel.setDiscontinued(false);
			inventoryLevel.setBackorderedUntil(null);
			faireClient.updateInventoryLevel(inventoryLevel);
		});
	}

	private void updateBackorderedOrders(List<Order> backorderedOrders) {
		// TODO Auto-generated method stub

	}

	private void updateProcessableOrders(List<Order> processableOrders) {
		// TODO Auto-generated method stub

	}

	private void updateProductOption(List<Order> processableOrders) {
		processableOrders.forEach(
				order -> order.getItems().forEach(
						item -> {
							Integer currentQuantity = inventoryRepository.getQuantity(item.getProductOptionId());
							Integer orderQuantity = item.getQuantity();
							Option updateOption = new Option();
							updateOption.setId(item.getProductOptionId());
							updateOption.setAvailableQuantity(orderQuantity - currentQuantity);
							updateOption.setUpdatedAt(ZoneDateTimeUtils.now());
							faireClient.updateProductOption(updateOption);
						})
				);
	}

}
