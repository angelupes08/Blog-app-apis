package com.lti.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){

        return  new OpenAPI()
                .info(new Info().title("Blog-App-Apis").description("This exposes APIs for Blog Application"))
                .addSecurityItem(new SecurityRequirement().addList("BlogappSecurityScheme"))
                .components(new Components().addSecuritySchemes("BlogappSecurityScheme", new SecurityScheme()
                        .name("BlogappSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
