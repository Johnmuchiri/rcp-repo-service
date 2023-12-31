package com.red.pharma.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${red.openapi.dev-url}")
    private String devUrl;

    @Value("${red.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("rdp-test@gmail.com");
        contact.setName("RedCare Pharmacy");
        contact.setUrl("https://www.redcare-pharmacy.com/de/about-us");

        Info info = new Info()
                .title("RedCare Pharmacy Repository service")
                .version("1.0")
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}