package com.niit.jdp.APIGateWay_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ApiGateWayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateWayServiceApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/song/**")
						.uri("lb://Song-service"))
				.route(p -> p.path("/user/**")
						.uri("lb://user-service"))
//				.route(p -> p.path("/emailSender/**")
//						.uri("lb://email-Service"))
//				.route(p -> p.path("/notification/**")
//						.uri("lb://notification-service"))
				.build();
	}
}
