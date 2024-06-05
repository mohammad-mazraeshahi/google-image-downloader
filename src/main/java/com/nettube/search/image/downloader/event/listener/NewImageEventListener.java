package com.nettube.search.image.downloader.event.listener;

import com.nettube.search.image.downloader.event.GetNewImageEvent;
import com.nettube.search.image.downloader.service.DownloaderService;
import com.nettube.search.image.downloader.service.ImageProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewImageEventListener {

    private final ImageProcessorService imageProcessorService;
    private final DownloaderService downloaderService;

    @Async
    @EventListener
    public void onApplicationEvent(GetNewImageEvent event) {
        log.debug("event: receive new images");

        Flux.fromIterable(event.getItems())
                .doOnNext(item -> {
                    String name = UUID.randomUUID().toString();
                    BufferedImage image = downloaderService.download(item.getLink());
                    BufferedImage resize = imageProcessorService.resize(image, 100, 100);
                    store(name, resize);
                })
                .onErrorContinue((throwable, o) -> log.error("error downloading image", throwable))
                .subscribe();
    }

    @SneakyThrows
    private static void store(String name, BufferedImage resize) {
        FileOutputStream fileOutputStream = new FileOutputStream("resize-" + name + ".jpg");
        ImageIO.write(resize, "jpg", fileOutputStream);
        fileOutputStream.flush();
    }

}