package com.rsaad.retailpro.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI retailProOpenApi() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("RetailPro API")
                                .description(
                                        "Enterprise Retail Platform REST API")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("RSAAD")
                                                .email("admin@retailpro.com")
                                )
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("RetailPro Documentation")
                );
    }

}