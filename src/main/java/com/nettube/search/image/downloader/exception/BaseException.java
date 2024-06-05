package com.nettube.search.image.downloader.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@Accessors(chain = true)
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class BaseException extends RuntimeException {
    public static final String MESSAGE = "exception.error.base";
    protected Object[] args;
    protected Integer subCode;

    public BaseException() {
        super(MESSAGE);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(MESSAGE, cause);
    }

    public BaseException setArgs(Object... args) {
        this.args = args;
        return this;
    }
}