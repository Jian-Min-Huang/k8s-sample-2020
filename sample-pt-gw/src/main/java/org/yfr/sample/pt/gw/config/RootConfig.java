package org.yfr.sample.pt.gw.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {

    @Value("${host.sample.lg.gw}")
    private String sampleLgGwHost;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, ApplicationContext context) {
        return builder.routes()
                .route(p -> p
                        .path("/item/**")
                        .uri(sampleLgGwHost))
                .route(p -> p
                        .path("/member/**")
                        .uri(sampleLgGwHost))
                .build();
    }

}
