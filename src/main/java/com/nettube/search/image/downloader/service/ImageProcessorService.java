package com.nettube.search.image.downloader.service;


import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public interface ImageProcessorService {

    BufferedImage resize(BufferedImage originalImage, int width, int height);

}
