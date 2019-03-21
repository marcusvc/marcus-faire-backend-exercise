package com.faire.marcus.exercise.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Order;

@Component
public class OrderRepository {
	
	private List<Order> processedOrders = new ArrayList<>();
	private List<Order> backorderedOrders = new ArrayList<>();
	
	public void addBackorderedOrder(Order order) {
		backorderedOrders.add(order);
	}
	
	public void clearBackorderedOrder() {
		backorderedOrders.clear();
	}
	
	public List<Order> getBackorderedOrders() {
		return new ArrayList<>(backorderedOrders);
	}
	
	public void addProcessedOrders(Order order) {
		processedOrders.add(order);
	}
	
	public void clearProcessedOrders() {
		processedOrders.clear();
	}
	
	public List<Order> getProcessedOrders() {
		return new ArrayList<>(processedOrders);
	}

}
