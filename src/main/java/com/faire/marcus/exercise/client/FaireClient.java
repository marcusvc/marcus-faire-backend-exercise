package com.faire.marcus.exercise.client;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.faire.marcus.exercise.context.TokenInputContext;
import com.faire.marcus.exercise.model.InventoryLevel;
import com.faire.marcus.exercise.model.Option;
import com.faire.marcus.exercise.model.Orders;
import com.faire.marcus.exercise.model.Products;

@Service
public class FaireClient {
	

	private static final Logger LOG = LoggerFactory.getLogger(FaireClient.class);
	
	private static final String X_FAIRE_ACCESS_TOKEN = "X-FAIRE-ACCESS-TOKEN";
	private static final String API_BASE_URI = "https://www.faire-stage.com";
	private static final String PRODUCT_RESOURCE_PATH = "/api/v1/products";
	private static final String ORDER_RESOURCE_PATH = "/api/v1/orders";
	private static final String OPTION_RESOURCE_PATH = "/api/v1/products/options";
	private static final String INVENTORY_LEVEL_RESOURCE_PATH = "/api/v1/products/options/inventory-levels";
	private static final String JERSEY_CONFIG_CLIENT_READ_TIMEOUT = "jersey.config.client.readTimeout";
	private static final String JERSEY_CONFIG_CLIENT_CONNECT_TIMEOUT = "jersey.config.client.connectTimeout";
	private static final int READ_TIMEOUT = 5000;
	private static final int CONNECT_TIMEOUT = 3000;
	
	private TokenInputContext tokenInputContext;
	
	public FaireClient(TokenInputContext tokenInputContext) {
		this.tokenInputContext = tokenInputContext;
	}
	
	public Products consumesAllProducts() {
		try {

			Client client = ClientBuilder.newClient()
					.property(JERSEY_CONFIG_CLIENT_CONNECT_TIMEOUT, CONNECT_TIMEOUT)
					.property(JERSEY_CONFIG_CLIENT_READ_TIMEOUT, READ_TIMEOUT);
			WebTarget brandTarget = client
					.target(new URI(API_BASE_URI))
					.path(PRODUCT_RESOURCE_PATH);
			
			Invocation.Builder invocationBuilder = brandTarget
					.request(MediaType.APPLICATION_JSON)
					.header(X_FAIRE_ACCESS_TOKEN, tokenInputContext.getToken());
			Response response = invocationBuilder.get();
			return response.readEntity(Products.class);

		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Orders consumesAllOrders() {
		try {

			Client client = ClientBuilder.newClient()
					.property(JERSEY_CONFIG_CLIENT_CONNECT_TIMEOUT, CONNECT_TIMEOUT)
					.property(JERSEY_CONFIG_CLIENT_READ_TIMEOUT, READ_TIMEOUT);
			WebTarget brandTarget = client
					.target(new URI(API_BASE_URI))
					.path(ORDER_RESOURCE_PATH);
			
			Invocation.Builder invocationBuilder = brandTarget
					.request(MediaType.APPLICATION_JSON)
					.header(X_FAIRE_ACCESS_TOKEN, tokenInputContext.getToken());
			Response response = invocationBuilder.get();
			return response.readEntity(Orders.class);

		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateProductOption(Option updateOption) {
		try {

			Client client = ClientBuilder.newClient()
					.property(JERSEY_CONFIG_CLIENT_CONNECT_TIMEOUT, CONNECT_TIMEOUT)
					.property(JERSEY_CONFIG_CLIENT_READ_TIMEOUT, READ_TIMEOUT);
			WebTarget brandTarget = client
					.target(new URI(API_BASE_URI))
					.path(OPTION_RESOURCE_PATH)
					.path(updateOption.getId())
					.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
			
			Invocation.Builder invocationBuilder = brandTarget
					.request(MediaType.APPLICATION_JSON)
					.header(X_FAIRE_ACCESS_TOKEN, tokenInputContext.getToken());
			Response response = invocationBuilder.method("PATCH", Entity.entity(updateOption, MediaType.APPLICATION_JSON));
			LOG.debug("Update product option quantity - HTTP status code: {}", response.getStatus());
			
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

	}

	public void updateInventoryLevel(InventoryLevel inventoryLevel) {
		try {

			Client client = ClientBuilder.newClient()
					.property(JERSEY_CONFIG_CLIENT_CONNECT_TIMEOUT, CONNECT_TIMEOUT)
					.property(JERSEY_CONFIG_CLIENT_READ_TIMEOUT, READ_TIMEOUT);
			WebTarget brandTarget = client
					.target(new URI(API_BASE_URI))
					.path(INVENTORY_LEVEL_RESOURCE_PATH)
					.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
			
			Invocation.Builder invocationBuilder = brandTarget
					.request(MediaType.APPLICATION_JSON)
					.header(X_FAIRE_ACCESS_TOKEN, tokenInputContext.getToken());
			Response response = invocationBuilder.method("PATCH", Entity.entity(inventoryLevel, MediaType.APPLICATION_JSON));
			LOG.debug("Update inventory level - HTTP status code: {}", response.getStatus());
			
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
