package com.stackroute.monitorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MonitorServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MonitorServiceApplication.class, args);
	}

}
