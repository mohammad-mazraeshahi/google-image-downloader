package com.nettube.search.image.downloader.transformer;

import com.nettube.search.image.downloader.dto.google.Item;
import com.nettube.search.image.downloader.model.Image;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class ImageTransformer {

    @SneakyThrows
    public static Image toEntity(Item item, BufferedImage img) {
        Image image = new Image();

        ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", imgBytes);
        image.setData(imgBytes.toByteArray());
        image.setUrl(item.getLink());
        image.setTitle(item.getTitle());

        return image;
    }
}
