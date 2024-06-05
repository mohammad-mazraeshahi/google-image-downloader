package com.nettube.search.image.downloader.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageInfo {
    private String contextLink;
    private int height;
    private int width;
    private int byteSize;
    private String thumbnailLink;
    private int thumbnailHeight;
    private int thumbnailWidth;
}