package com.nettube.search.image.downloader.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = Image.TABLE_NAME)
public class Image extends AbstractEntity<Long> {
    public static final String TABLE_NAME = "image";

    private String title;
    private String url;
    @Lob
    private byte[] data;
}
