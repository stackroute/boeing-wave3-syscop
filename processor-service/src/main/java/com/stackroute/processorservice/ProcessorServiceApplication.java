package com.stackroute.processorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProcessorServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProcessorServiceApplication.class, args);

	}

}
