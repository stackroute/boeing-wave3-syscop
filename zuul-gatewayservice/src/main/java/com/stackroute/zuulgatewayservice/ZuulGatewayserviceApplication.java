package com.stackroute.zuulgatewayservice;

//import com.stackroute.zuulgatewayservice.config.HttpRedirect;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;




@CrossOrigin("*")
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayserviceApplication {



//	@Bean
//	public HttpRedirect httpRedirect1() {
//		return new HttpRedirect();
//	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayserviceApplication.class, args);
	}
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}




}
