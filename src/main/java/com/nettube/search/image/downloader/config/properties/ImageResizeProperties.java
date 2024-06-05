package com.nettube.search.image.downloader.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "image.resize")
public class ImageResizeProperties {
    private Integer width = 100;
    private Integer height = 100;
}