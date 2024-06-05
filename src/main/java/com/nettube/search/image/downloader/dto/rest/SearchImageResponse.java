package com.nettube.search.image.downloader.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchImageResponse {
    private List<ImageResponse> images = new ArrayList<>();
}
