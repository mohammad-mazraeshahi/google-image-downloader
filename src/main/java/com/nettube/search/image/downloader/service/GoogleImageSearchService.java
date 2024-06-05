package com.nettube.search.image.downloader.service;


import com.nettube.search.image.downloader.client.GoogleCustomSearchClient;
import com.nettube.search.image.downloader.config.properties.GoogleCustomSearchProperties;
import com.nettube.search.image.downloader.dto.google.Item;
import com.nettube.search.image.downloader.dto.rest.SearchImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GoogleImageSearchService implements GoogleSearchService {
    private final GoogleCustomSearchClient googleCustomSearchClient;
    private final GoogleCustomSearchProperties googleCustomSearchProperties;

    @Override
    public Mono<List<Item>> search(SearchImageRequest request) {
        int pageCount = getPageCount(request.getCount());
        ArrayList<Item> items = new ArrayList<>();

        return Flux.range(0, pageCount)
                .flatMap(pageNo -> {
                    int perPage = pageNo == pageCount - 1 ?
                            request.getCount() % googleCustomSearchProperties.getPerPage() :
                            googleCustomSearchProperties.getPerPage();

                    return googleCustomSearchClient.search(request.getQ(), getIndex(pageNo), perPage);
                })
                .doOnNext(result -> items.addAll(result.getItems()))
                .then(Mono.just(items));
    }

    private int getPageCount(Integer count) {
        int pageCount = count / googleCustomSearchProperties.getPerPage();

        return (count % googleCustomSearchProperties.getPerPage() == 0) ? pageCount : pageCount + 1;
    }


    private int getIndex(int pageNo) {
        return pageNo * googleCustomSearchProperties.getPerPage() + 1;
    }

}
