package com.stackroute.processorservice;

import com.stackroute.processorservice.service.InfluxServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessorServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProcessorServiceApplication.class, args);

	}

}
