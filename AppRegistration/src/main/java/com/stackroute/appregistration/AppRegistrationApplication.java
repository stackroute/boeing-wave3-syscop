package com.stackroute.appregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AppRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRegistrationApplication.class, args);
	}

}
