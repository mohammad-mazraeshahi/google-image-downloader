package com.nettube.search.image.downloader.client;

import com.nettube.search.image.downloader.config.properties.GoogleCustomSearchProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Component
public class GoogleCustomSearchClient {
    private final static String SEARCH_TYPE = "image";
    private final WebClient webClient;
    private final GoogleCustomSearchProperties googleCustomSearchProperties;

    public GoogleCustomSearchClient(WebClient.Builder webClientBuilder, GoogleCustomSearchProperties googleCustomSearchProperties) {
        this.webClient = webClientBuilder.baseUrl(googleCustomSearchProperties.getBaseUrl().toString())
                .build();
        this.googleCustomSearchProperties = googleCustomSearchProperties;
    }


    public Mono<Map<String, Object>> search(String query) {
        return search(query, null);
    }


    public Mono<Map<String, Object>> search(String query, Long startIndex) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/customsearch/v1")
                        .queryParam("key", googleCustomSearchProperties.getApiKey())
                        .queryParam("cx", googleCustomSearchProperties.getCx())
                        .queryParam("searchType", SEARCH_TYPE)
                        .queryParam("q", query)
                        .queryParamIfPresent("rights", Optional.ofNullable(googleCustomSearchProperties.getRights()))
                        .queryParamIfPresent("num", Optional.ofNullable(googleCustomSearchProperties.getPerPage()))
                        .queryParamIfPresent("start", Optional.ofNullable(startIndex))
                        .build())
                .retrieve()
                .bodyToMono(ParameterizedTypeReference.forType(Map.class));
    }
}
