package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MonthlyExpenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonthlyExpenseApplication.class, args);
	}

}
