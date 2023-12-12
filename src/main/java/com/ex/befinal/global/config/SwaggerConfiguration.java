package com.ex.befinal.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.lang.reflect.Type;
import jdk.javadoc.doclet.Doclet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
        .info(new Info()
            .title("이슈맵 API")
            .version("1.0.0"))
        .components(new Components()
            .addSecuritySchemes("bearer-key",
                new io.swagger.v3.oas.models.security.SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
  }
}
