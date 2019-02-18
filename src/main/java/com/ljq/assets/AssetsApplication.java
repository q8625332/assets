package com.ljq.assets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.ljq.assets.mapper")
public class AssetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetsApplication.class, args);
	}

}
