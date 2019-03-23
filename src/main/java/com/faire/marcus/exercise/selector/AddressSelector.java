package com.faire.marcus.exercise.selector;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.model.Address;
import com.faire.marcus.exercise.model.Orders;

@Component
public class AddressSelector implements Selector<Address, Orders> {

	@Override
	public List<Address> select(Orders orders) {
		return orders.getOrders().stream()
				.flatMap(order -> Stream.<Address>of(order.getAddress()))
				.collect(Collectors.toList());
	}

}
