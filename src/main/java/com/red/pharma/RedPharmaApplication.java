package com.red.pharma;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
@SecurityScheme(name = "X-API-KEY", scheme = "apikey", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class RedPharmaApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedPharmaApplication.class, args);
	}
}
