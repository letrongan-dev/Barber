package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.myproject")
public class BaberApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaberApplication.class, args);
	}

}
