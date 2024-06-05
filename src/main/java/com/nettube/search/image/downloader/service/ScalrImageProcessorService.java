package com.nettube.search.image.downloader.service;


import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class ScalrImageProcessorService implements ImageProcessorService {

    @Override
    public BufferedImage resize(BufferedImage originalImage, int width, int height) {
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, width, height);
    }

}
