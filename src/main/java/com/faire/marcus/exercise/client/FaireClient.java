package com.faire.marcus.exercise.client;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.springframework.stereotype.Service;

import com.faire.marcus.exercise.context.InputContext;
import com.faire.marcus.exercise.model.Brand;
import com.faire.marcus.exercise.model.Products;

@Service
public class FaireClient {
	
	private static final String API_BASE_URI = "https://www.faire-stage.com/api";
	private static final String BRAND_PATH = "brand";
	private static final String X_FAIRE_ACCESS_TOKEN = "X-FAIRE-ACCESS-TOKEN";
	
	private InputContext inputContext;
	
	public FaireClient(InputContext inputContext) {
		this.inputContext = inputContext;
	}
	
	public Products consumesAllProductsForAGivenBrand(Brand brand) {
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class));
			WebTarget brandTarget = client.target(new URI(API_BASE_URI)).path(BRAND_PATH).path(brand.getId());
			
			Invocation.Builder invocationBuilder = brandTarget.request(MediaType.APPLICATION_JSON).header(X_FAIRE_ACCESS_TOKEN, inputContext.getToken());
			return invocationBuilder.get(Products.class);

		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
