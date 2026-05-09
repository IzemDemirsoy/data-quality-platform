package com.example.rule_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;


@SpringBootApplication
public class RuleManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuleManagementServiceApplication.class, args);
	}
}