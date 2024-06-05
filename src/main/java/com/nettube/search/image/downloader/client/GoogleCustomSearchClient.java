package com.nettube.search.image.downloader.client;

import com.nettube.search.image.downloader.config.properties.GoogleCustomSearchProperties;
import com.nettube.search.image.downloader.dto.google.GoogleCustomSearchResult;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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


    public Mono<GoogleCustomSearchResult> search(String query) {
        return search(query, null);
    }

    public Mono<GoogleCustomSearchResult> search(String query, Integer startIndex) {
        return search(query, startIndex, googleCustomSearchProperties.getPerPage());
    }

    public Mono<GoogleCustomSearchResult> search(String query, Integer startIndex, Integer perPage) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/customsearch/v1")
                        .queryParam("key", googleCustomSearchProperties.getApiKey())
                        .queryParam("cx", googleCustomSearchProperties.getCx())
                        .queryParam("searchType", SEARCH_TYPE)
                        .queryParam("q", query)
                        .queryParamIfPresent("rights", Optional.ofNullable(googleCustomSearchProperties.getRights()))
                        .queryParamIfPresent("num", Optional.ofNullable(perPage))
                        .queryParamIfPresent("start", Optional.ofNullable(startIndex))
                        .build())
                .retrieve()
                .bodyToMono(GoogleCustomSearchResult.class);
    }
}
