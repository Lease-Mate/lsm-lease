package com.lsm.ws.lease.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String LSM_LEASE_GROUP = "lsm-lease";
    private static final String LSM_LEASE_PATH = "/v1/api/lease/**";

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                             .group(LSM_LEASE_GROUP)
                             .pathsToMatch(LSM_LEASE_PATH)
                             .build();
    }

    @Bean
    public OpenAPI openAPI() {
        var openApi = new OpenAPI().info(new Info().description("LeaseMate lease microservice")
                                                   .title("Lease microservice"))
                                   .addServersItem(new Server().url("/"));
        addAuthBearer(openApi);
        return openApi;
    }

    private void addAuthBearer(OpenAPI openAPI) {
        openAPI.addSecurityItem(new SecurityRequirement().addList("authBearer"))
               .components(new Components().addSecuritySchemes("authBearer",
                       new SecurityScheme().name("authBearer")
                                           .type(SecurityScheme.Type.HTTP)
                                           .scheme("bearer")
                                           .bearerFormat("JWT")));
    }
}
