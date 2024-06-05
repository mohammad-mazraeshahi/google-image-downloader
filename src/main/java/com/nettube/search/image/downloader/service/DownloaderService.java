package com.nettube.search.image.downloader.service;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;
import java.net.URL;

@Service
public interface DownloaderService {

    Mono<BufferedImage> download(String url);

    Mono<BufferedImage> download(URL url);

}
