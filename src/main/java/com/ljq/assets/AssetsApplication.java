package com.ljq.assets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AssetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetsApplication.class, args);
	}

}
