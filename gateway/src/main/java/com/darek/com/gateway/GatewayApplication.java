package com.darek.com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args); }


        @Bean
        RouteLocator gateway(RouteLocatorBuilder rlb){
            return rlb.routes()
                .route(routeSpec -> routeSpec
                    .path("/user")
                    .uri("http://172.20.0.3:8085/user:80"))
//                    .uri("http://start.spring.io:80/"))  http://localhost:8761/
                .build();
        }
    }
