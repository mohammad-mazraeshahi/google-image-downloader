package com.nettube.search.image.downloader.service.impl;


import com.nettube.search.image.downloader.service.ImageProcessorService;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@Service
public class ScalrImageProcessorService implements ImageProcessorService {

    @Override
    public Mono<BufferedImage> resize(BufferedImage originalImage, int width, int height) {
        return Mono.just(Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, width, height));
    }

}
