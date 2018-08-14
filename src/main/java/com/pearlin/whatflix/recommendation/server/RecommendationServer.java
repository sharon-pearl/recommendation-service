package com.pearlin.whatflix.recommendation.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.pearlin.whatflix.recommendation.server",
		"com.pearlin.whatflix.recommendation.service" })
public class RecommendationServer {

	private static final Logger logger = LoggerFactory.getLogger(RecommendationServer.class);

	public static void main(String... args) {
		SpringApplication.run(RecommendationServer.class, args);
		logger.info("Recommendation server started");
	}

}