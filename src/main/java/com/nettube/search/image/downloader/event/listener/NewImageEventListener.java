package com.nettube.search.image.downloader.event.listener;

import com.nettube.search.image.downloader.config.properties.ImageResizeProperties;
import com.nettube.search.image.downloader.dto.google.Item;
import com.nettube.search.image.downloader.event.GetNewImageEvent;
import com.nettube.search.image.downloader.exception.BaseException;
import com.nettube.search.image.downloader.exception.ImageExistException;
import com.nettube.search.image.downloader.model.Image;
import com.nettube.search.image.downloader.repository.ImageRepository;
import com.nettube.search.image.downloader.service.DownloaderService;
import com.nettube.search.image.downloader.service.ImageProcessorService;
import com.nettube.search.image.downloader.transformer.ImageTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.awt.image.BufferedImage;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewImageEventListener {
    private final ImageProcessorService imageProcessorService;
    private final DownloaderService downloaderService;
    private final ImageRepository imageRepository;
    private final ImageResizeProperties imageResizeProperties;

    @Async
    @EventListener
    public void onApplicationEvent(GetNewImageEvent event) {
        log.info("event: receive new images items search");

        Flux.fromIterable(event.getItems())
                .doOnNext(this::checkExist)
                .flatMap(item -> downloaderService.download(item.getLink())
                        .flatMap(image -> imageProcessorService.resize(image, imageResizeProperties.getWidth(), imageResizeProperties.getHeight()))
                        .doOnNext(resizeImage -> store(item, resizeImage))
                        .doOnSuccess(o -> log.info("image: {} ,download and store successfully.", item.getLink()))
                )
                .onErrorContinue((throwable, o) -> errorHandler(throwable))
                .doOnComplete(() -> log.info("Well done. Got all image ;)"))
                .subscribe();
    }

    private static void errorHandler(Throwable throwable) {
        if (throwable instanceof BaseException e) {
            log.warn("image: {} ,{}", e.getArgs(), e.getMessage());
            return;
        }

        log.error("error downloading image", throwable);
    }

    private void store(Item item, BufferedImage img) {
        Image image = ImageTransformer.toEntity(item, img);

        imageRepository.save(image);
    }

    private void checkExist(Item item) {
        if (imageRepository.existsByUrl(item.getLink())) {
            throw new ImageExistException().setArgs(item.getLink());
        }
    }
}