package com.ycode.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ycode.stock"}) 
public class FutureBullApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutureBullApplication.class, args);
	}

}
