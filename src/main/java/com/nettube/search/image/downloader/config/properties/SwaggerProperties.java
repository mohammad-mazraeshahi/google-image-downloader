package com.nettube.search.image.downloader.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private String description;
    private String version;
    private String title;
    @NestedConfigurationProperty
    private List<Servers> servers = new ArrayList<>();
}