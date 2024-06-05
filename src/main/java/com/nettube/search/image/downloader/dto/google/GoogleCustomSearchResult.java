package com.nettube.search.image.downloader.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleCustomSearchResult {
    private String kind;
    private Queries queries;
    private ArrayList<Item> items = new ArrayList<>();
}
