package com.nettube.search.image.downloader.service;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public interface GoogleSearchService {

    Mono<Map<String, Object>> search(String query);

}
