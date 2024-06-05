package com.nettube.search.image.downloader.exception;

import lombok.Getter;

@Getter
public class DownloadFileException extends BaseException {
    public static final String MESSAGE = "can not get file from url";

    public DownloadFileException() {
        super(MESSAGE);
    }

    public DownloadFileException(String message) {
        super(message);
    }

    public DownloadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadFileException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public DownloadFileException setArgs(Object... args) {
        this.args = args;
        return this;
    }
}
