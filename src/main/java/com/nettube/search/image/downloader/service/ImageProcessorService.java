package com.nettube.search.image.downloader.service;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@Service
public interface ImageProcessorService {

    Mono<BufferedImage> resize(BufferedImage originalImage, int width, int height);

}
