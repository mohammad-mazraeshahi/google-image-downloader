package com.nettube.search.image.downloader.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URL;

@Data
@Component
@ConfigurationProperties(prefix = "google")
public class GoogleCustomSearchProperties {
    private URL baseUrl;
    private String apiKey;
    private String rights;
    private String cx;
    private Integer perPage;
}
