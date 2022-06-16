package ru.cs.vsu.ast2_backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Configuration
public class OpenApiConfiguration {

    @Value("${openapi.server.url}")
    private String serverUrl;

    @Value("${openapi.info.title}")
    private String title;

    @Value("${openapi.info.description}")
    private String description;

    static {
        SpringDocUtils.getConfig().replaceWithClass(LocalTime.class, String.class);
    }

    @PostConstruct
    private void logSwagger() {
        log.info("Swagger was running on {}", serverUrl + "/swagger-ui/index.html");
    }

    @Bean
    public OpenAPI openApi() {
        var server = new Server();
        server.setUrl(serverUrl);

        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description))
                .servers(List.of(server))
                .components(new Components()
                        .addSecuritySchemes("api_key", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .description("Api Key access")
                                .in(SecurityScheme.In.HEADER)
                                .name(HttpHeaders.AUTHORIZATION)
                        ))
                .security(List.of(new SecurityRequirement().addList("api_key")));
    }


}

