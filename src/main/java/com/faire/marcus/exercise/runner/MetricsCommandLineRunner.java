package com.faire.marcus.exercise.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.faire.marcus.exercise.context.TokenInputContext;
import com.faire.marcus.exercise.controller.MetricsController;

@Component
public class MetricsCommandLineRunner implements CommandLineRunner {
	
	private static final Logger LOG = LoggerFactory.getLogger(MetricsCommandLineRunner.class);
	
	private TokenInputContext inputContext;
	private MetricsController metricsController;
	
	public MetricsCommandLineRunner(TokenInputContext inputContext, MetricsController metricsController) {
		this.inputContext = inputContext;
		this.metricsController = metricsController;
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.debug("Command line running");
		if (args != null && args.length == 1) {
			inputContext.setToken(args[0]);
			metricsController.execute();
		} else {
			LOG.debug("Input not acceptable: {}", args);
		}
	}

}
