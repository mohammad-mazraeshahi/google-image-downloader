package com.nettube.search.image.downloader.service;


import com.nettube.search.image.downloader.dto.google.Item;
import com.nettube.search.image.downloader.dto.rest.SearchImageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface GoogleSearchService {

    Mono<List<Item>> search(SearchImageRequest request);

}
