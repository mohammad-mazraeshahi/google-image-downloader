package com.nettube.search.image.downloader.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPage {
    private String title;
    private String totalResults;
    private String searchTerms;
    private int count;
    private int startIndex;
    private String inputEncoding;
    private String outputEncoding;
    private String safe;
    private String cx;
    private String searchType;
}