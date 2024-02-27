package com.ty.shoppers.stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ShoppersStackApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(ShoppersStackApplication.class, args);
//		SpringApplication.exit(applicationContext, ()->0);
	}

}
