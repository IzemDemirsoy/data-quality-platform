package com.example.external_user_collector_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExternalUserCollectorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExternalUserCollectorServiceApplication.class, args);
	}
}