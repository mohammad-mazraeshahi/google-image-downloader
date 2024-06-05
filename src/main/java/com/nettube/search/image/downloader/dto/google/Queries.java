package com.nettube.search.image.downloader.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Queries {
    private ArrayList<RequestPage> request = new ArrayList<>();
    private ArrayList<RequestPage> nextPage = new ArrayList<>();
}