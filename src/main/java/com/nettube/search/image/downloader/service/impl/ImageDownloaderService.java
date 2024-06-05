package com.nettube.search.image.downloader.service.impl;


import com.nettube.search.image.downloader.exception.FileTypeNotSupportException;
import com.nettube.search.image.downloader.service.DownloaderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class ImageDownloaderService implements DownloaderService {

    @SneakyThrows
    @Override
    public BufferedImage download(String url) {
        return download(new URL(url));
    }

    @SneakyThrows
    @Override
    public BufferedImage download(URL url) {
        try {
            return ImageIO.read(url);
        } catch (Exception e) {
            throw new FileTypeNotSupportException(e);
        }
    }

}
