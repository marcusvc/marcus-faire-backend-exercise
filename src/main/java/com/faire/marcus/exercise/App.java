package com.faire.marcus.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		args = new String[] {"HQLA9307HSLQYTC24PO2G0LITTIOHS2MJC8120PVZ83HJK4KACRZJL91QB7K01NWS2TUCFXGCHQ8HVED8WNZG0KS6XRNBFRNGY71"};
		SpringApplication.run(App.class, args);
		LOGGER.debug("Running marcus-faire-backend-exercise");
	}

}
