package com.faire.marcus.exercise.controller;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.client.FaireClient;
import com.faire.marcus.exercise.repository.InventoryRepository;
import com.faire.marcus.exercise.repository.OrderRepository;

@Component
public class MetricsController {
	
	private InventoryRepository inventoryRepository;
	private OrderRepository orderRepository;
	private FaireClient faireClient;
	
	public MetricsController(InventoryRepository inventoryRepository, OrderRepository orderRepository, FaireClient faireClient) {
		this.inventoryRepository = inventoryRepository;
		this.orderRepository = orderRepository;
		this.faireClient = faireClient;
	}
	
	public void execute() {
		
	}

}
