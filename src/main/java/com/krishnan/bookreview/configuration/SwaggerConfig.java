package com.krishnan.bookreview.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI defineApi()
    {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Book Review Application");

        Info info= new Info().title("BookReview").version("1.0").description("Swagger testing phase");

        return new OpenAPI().addServersItem(server).info(info);
    }
}
