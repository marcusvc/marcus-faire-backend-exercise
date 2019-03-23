package com.faire.marcus.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	/*
	 * Here Spring-Boot bootstrap the application
	 * SpringApplication injects args in every CommandLineRunner implementation and executes run method
	 * In this application the class MetricsCommandLineRunner is the only one implementing CommandLineRunner
	 * Execution goes on at class MetricsCommandLineRunner
	 */
	public static void main(String[] args) {
		LOG.debug("Running marcus-faire-backend-exercise");
		LOG.debug("Input parameter: {}", args);
		SpringApplication.run(App.class, args);
	}

}
