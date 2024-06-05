package com.nettube.search.image.downloader.exception;

import lombok.Getter;

@Getter
public class FileTypeNotSupportException extends BaseException {
    public static final String MESSAGE = "file type not supported";

    public FileTypeNotSupportException() {
        super(MESSAGE);
    }

    public FileTypeNotSupportException(String message) {
        super(message);
    }

    public FileTypeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeNotSupportException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public FileTypeNotSupportException setArgs(Object... args) {
        this.args = args;
        return this;
    }
}
