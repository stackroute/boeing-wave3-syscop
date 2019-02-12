package com.stackroute.nextevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NexteventApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexteventApplication.class, args);
	}

}

