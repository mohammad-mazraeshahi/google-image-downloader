package com.nettube.search.image.downloader.exception;

import lombok.Getter;

@Getter
public class ImageExistException extends BaseException {
    public static final String MESSAGE = "image exist";

    public ImageExistException() {
        super(MESSAGE);
    }

    public ImageExistException(String message) {
        super(message);
    }

    public ImageExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageExistException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public ImageExistException setArgs(Object... args) {
        this.args = args;
        return this;
    }
}
