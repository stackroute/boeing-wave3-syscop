package com.stackroute.jwtauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class JwtauthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtauthenticationApplication.class, args);
	}

}

