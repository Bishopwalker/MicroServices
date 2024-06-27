package com.nngc.microservices.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {


    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/product/**")
//                        .filters(f -> f.prefixPath("/api")
//                                .addResponseHeader("X-Keeping-Up", "Gotta Micro Your Services Bishop")
//                        )
//                        .uri("lb://product-service")
//                )
//                .route(r -> r.path("/orders/**")
//                        .filters(f -> f.prefixPath("/api")
//                                .addResponseHeader("X-Keeping-Up", "Gotta Micro Your Services Bishop")
//                        )
//                        .uri("lb://order-service")
//                )
//                .build();
//    }
}
