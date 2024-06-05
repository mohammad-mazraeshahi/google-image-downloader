package com.nettube.search.image.downloader.config;

import com.nettube.search.image.downloader.config.properties.SwaggerProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class OpenApiConfiguration implements OpenApiCustomizer {

    private final SwaggerProperties swaggerProperties;

    @Bean
    public OpenAPI openApiInformation() {
        return new OpenAPI()
                .info(getInfo())
                .servers(getServerList());
    }


    @Override
    public void customise(OpenAPI openApi) {

    }

    protected Info getInfo() {
        return new Info()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion());
    }

    protected List<Server> getServerList() {
        return swaggerProperties.getServers().stream()
                .map(server -> new Server()
                        .url(server.getUrl())
                        .description(server.getDescription())
                ).collect(Collectors.toList());
    }
}