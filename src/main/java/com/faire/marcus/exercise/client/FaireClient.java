package com.faire.marcus.exercise.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.logging.LoggingFeature;
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
	
	private TokenInputContext tokenInputContext;
	
	public FaireClient(TokenInputContext tokenInputContext) {
		this.tokenInputContext = tokenInputContext;
	}
	
	public Products consumesAllProducts() {
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class));
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

			Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class));
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

			Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class));
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

			Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFeature.class));
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
	
	private Client getNonSecureClient() throws Exception {

	    SSLContext sslcontext = SSLContext.getInstance("TLS");

	    sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
	        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
	        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
	        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
	    }}, new java.security.SecureRandom());

	    return ClientBuilder.newBuilder()
	                        .sslContext(sslcontext)
	                        .hostnameVerifier((s1, s2) -> true)
	                        .build();
	}

}
