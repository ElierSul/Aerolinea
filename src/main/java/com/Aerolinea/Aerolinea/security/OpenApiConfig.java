package com.Aerolinea.Aerolinea.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .security(Arrays.asList(
                        new SecurityRequirement().addList("spring_oauth")))
                .info(new Info()
                        .title("AeroExpressLatam API")
                        .description("Bienvenido al sistema de reserva de vuelos AeroExpressLatam." +
                                "A continuacion encontraras los endpoints necesarios asi como la explicacion de estos " +
                                "para qeu puedas tener una mejor experiencia de desarrollo y usabilidad de nuestra API." +
                                " Gracias por preferirnos. Si estas interesado en nuestros desarrolladores o software puedes contactarnos: " +
                                "David Rodriguez: davidrodrilopez2002@gmail.com  Valentina Valencia: valentinaquejada16@gmail.com " +
                                "Elier Sulbara: sulbaraantonio@gmail.com")
                        .contact(new Contact().email("sulbaraantonio@gmail.com").name("Developer: Elier Sulbara"))
                        .version("1.0")
                );
    }
}