package com.nettube.search.image.downloader.service.impl;


import com.nettube.search.image.downloader.exception.DownloadFileException;
import com.nettube.search.image.downloader.exception.FileTypeNotSupportException;
import com.nettube.search.image.downloader.service.DownloaderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class ImageDownloaderService implements DownloaderService {

    @SneakyThrows
    @Override
    public Mono<BufferedImage> download(String url) {
        return download(new URL(url));
    }

    @SneakyThrows
    @Override
    public Mono<BufferedImage> download(URL url) {
        try {
            return Mono.just(ImageIO.read(url));
        } catch (IIOException e) {
            throw new DownloadFileException(e).setArgs(url);
        } catch (Exception e) {
            throw new FileTypeNotSupportException(e).setArgs(url);
        }
    }

}
