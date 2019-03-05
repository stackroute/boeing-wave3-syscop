package com.stackroute.threshholdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ThreshholdServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreshholdServiceApplication.class, args);
	}

}
