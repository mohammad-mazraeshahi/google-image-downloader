package com.nettube.search.image.downloader.controller;

import com.nettube.search.image.downloader.service.GoogleSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final GoogleSearchService googleSearchService;

    @GetMapping("/image")
    public Mono<ResponseEntity<?>> searchImages(String q) {
        return googleSearchService.search(q)
                .map(ResponseEntity::ok);
    }

}
