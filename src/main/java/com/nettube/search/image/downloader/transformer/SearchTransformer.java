package com.nettube.search.image.downloader.transformer;

import com.nettube.search.image.downloader.dto.google.GoogleCustomSearchResult;
import com.nettube.search.image.downloader.dto.google.Item;
import com.nettube.search.image.downloader.dto.rest.ImageResponse;
import com.nettube.search.image.downloader.dto.rest.SearchImageResponse;

import java.util.List;

public class SearchTransformer {

    public static SearchImageResponse toSearchImageResponse(GoogleCustomSearchResult googleCustomSearchResult) {
        return toSearchImageResponse(googleCustomSearchResult.getItems());
    }

    public static SearchImageResponse toSearchImageResponse(List<Item> items) {
        List<ImageResponse> list = items.stream()
                .map(item -> new ImageResponse(item.getLink()))
                .toList();

        return new SearchImageResponse(list);
    }
}
