package com.stackroute.datacollectorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DataCollectorServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(DataCollectorServiceApplication.class, args);
	}

}
