package com.utn.productos_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Gestión de Productos")
                        .version("1.0")
                        .description("API REST completa para la gestión de productos de un e-commerce. " +
                                "Permite realizar operaciones CRUD sobre productos, filtrar por categoría " +
                                "y actualizar el stock de manera individual")
                        .contact(new Contact()
                                .name("Victoria Acosta - UTN Desarrollo de Software 2025")
                                .url("https://github.com/VickyA7"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}