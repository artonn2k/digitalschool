package com.zerogravitysolutions.digitalschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DigitalschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalschoolApplication.class, args);
	}

}
