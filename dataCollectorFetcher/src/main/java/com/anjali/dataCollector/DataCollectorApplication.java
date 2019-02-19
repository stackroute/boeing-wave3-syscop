package com.anjali.dataCollector;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class DataCollectorApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder (DataCollectorApplication.class).bannerMode(Banner.Mode.OFF).run(args);
	}

}

