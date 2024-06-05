package com.nettube.search.image.downloader.controller;

import com.nettube.search.image.downloader.dto.rest.SearchImageRequest;
import com.nettube.search.image.downloader.service.GoogleSearchService;
import com.nettube.search.image.downloader.transformer.SearchTransformer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final GoogleSearchService googleSearchService;

    @GetMapping("/image")
    public Mono<ResponseEntity<?>> searchImages(@Valid SearchImageRequest request) {
        return googleSearchService.search(request)
                .map(googleCustomSearchResult -> ResponseEntity.ok(SearchTransformer.toSearchImageResponse(googleCustomSearchResult)));
    }

}
