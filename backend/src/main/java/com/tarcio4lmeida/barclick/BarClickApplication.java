package com.tarcio4lmeida.barclick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BarClickApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarClickApplication.class, args);
	}

}
