package com.faire.marcus.exercise.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.client.FaireClient;
import com.faire.marcus.exercise.model.Option;
import com.faire.marcus.exercise.model.Order;
import com.faire.marcus.exercise.model.Orders;
import com.faire.marcus.exercise.model.Product;
import com.faire.marcus.exercise.model.Products;
import com.faire.marcus.exercise.repository.InventoryRepository;
import com.faire.marcus.exercise.selector.BackorderedSelector;
import com.faire.marcus.exercise.selector.ProcessableSelector;
import com.faire.marcus.exercise.selector.TeaDropSelector;
import com.faire.marcus.exercise.util.ZoneDateTimeUtils;

@Component
public class MetricsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MetricsController.class);
	
	private InventoryRepository inventoryRepository;
	private FaireClient faireClient;
	private TeaDropSelector teaDropSelector;
	private BackorderedSelector backorderedSelector;
	private ProcessableSelector processableSelector;
	
	public MetricsController(InventoryRepository inventoryRepository, FaireClient faireClient,
			TeaDropSelector teaDropSelector, BackorderedSelector backorderedSelector, ProcessableSelector processableSelector) {
		this.inventoryRepository = inventoryRepository;
		this.faireClient = faireClient;
		this.teaDropSelector = teaDropSelector;
		this.backorderedSelector = backorderedSelector;
		this.processableSelector = processableSelector;
	}
	
	public void execute() {
		Products allProducts = faireClient.consumesAllProducts();
		List<Product> teaDropProducts = teaDropSelector.select(allProducts);
		LOG.info("Tea Drop products: {}", teaDropProducts);
		inventoryRepository.add(teaDropProducts);
		LOG.info("Inventory: {}", inventoryRepository.getAll());
		Orders allOrders = faireClient.consumesAllOrders();
		LOG.info("All orders: {}", allOrders.getOrders());
		List<Order> backorderedOrders = backorderedSelector.select(allOrders);
		LOG.info("Backordered: {}", backorderedOrders);
		updateBackorderedOrders(backorderedOrders);
		List<Order> processableOrders = processableSelector.select(allOrders);
		LOG.info("Processable Orders: {}", processableOrders);
		updateProcessableOrders(processableOrders);
		updateProductOption(processableOrders);
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
