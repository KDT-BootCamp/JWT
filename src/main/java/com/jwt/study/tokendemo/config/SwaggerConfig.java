package com.jwt.study.tokendemo.config;

import org.hibernate.mapping.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

//Bearer token 사용설정

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {
        String jwt = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Components components = 
            new Components().addSecuritySchemes(jwt,
                                                new SecurityScheme()
                                                .name(jwt)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                );
   
        return new OpenAPI()
            .components(components)                                        
            .info(apiInfo())
            .addSecurityItem(securityRequirement);
        }

        private Info apiInfo() {
            return new Info().title("Spring Security + JWT API")
                            .description("KDT Swagger Token UI")
                            .version("1.0.0") ;
        }
}
