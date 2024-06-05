package com.nettube.search.image.downloader.event;

import com.nettube.search.image.downloader.dto.google.Item;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class GetNewImageEvent extends ApplicationEvent {
    private final List<Item> items;

    public GetNewImageEvent(Object source, List<Item> items) {
        super(source);
        this.items = Objects.isNull(items) ? new ArrayList<>() : items;
    }

}