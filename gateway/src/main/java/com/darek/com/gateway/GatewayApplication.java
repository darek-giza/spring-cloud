package com.darek.com.gateway;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args); }

        @Bean
        RouteLocator gateway(RouteLocatorBuilder rlb){
            String ipAddr = eurekaClient.getNextServerFromEureka("user-service", false).getIPAddr();
            String uri = "http://" + ipAddr + ":8085/user";
            return rlb.routes()
                .route(routeSpec -> routeSpec
                    .path("/user-service/user-get")
                    .uri(uri))
                .build();
        }
    }
