package com.stackroute.AppRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

=======
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
>>>>>>> d4893e1a6661654a84f70f0dcbd23bbe66c4302d
@SpringBootApplication
public class AppRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRegistrationApplication.class, args);
	}

}
