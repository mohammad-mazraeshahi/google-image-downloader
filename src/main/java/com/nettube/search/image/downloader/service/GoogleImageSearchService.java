package com.nettube.search.image.downloader.service;


import com.nettube.search.image.downloader.client.GoogleCustomSearchClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class GoogleImageSearchService implements GoogleSearchService {
    private final GoogleCustomSearchClient googleCustomSearchClient;

    @Override
    public Mono<Map<String, Object>> search(String query) {
        return googleCustomSearchClient.search(query);
    }

}
