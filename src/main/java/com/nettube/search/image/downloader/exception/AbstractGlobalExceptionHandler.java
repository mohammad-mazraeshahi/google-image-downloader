package com.nettube.search.image.downloader.exception;

import com.nettube.search.image.downloader.dto.ErrorResponseDto;
import com.nettube.search.image.downloader.transformer.ValidationTransform;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public abstract class AbstractGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .message(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase().toLowerCase())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .errors(ValidationTransform.toErrorDetail(exception))
                .build();

        log.debug("validation exception: {}", response, exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleValidationException(ConstraintViolationException exception) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .message(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase().toLowerCase())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .errors(ValidationTransform.toErrorDetail(exception.getConstraintViolations()))
                .build();

        log.debug("validation exception: {}", response, exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleValidationException(WebExchangeBindException exception) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .message(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase().toLowerCase())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .errors(ValidationTransform.toErrorDetail(exception.getFieldErrors()))
                .build();

        log.debug("validation exception: {}", response, exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }


    @ExceptionHandler
    public ResponseEntity<?> httpMessageNotReadableError(HttpMessageNotReadableException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDto response = ErrorResponseDto.builder()
                .message("required request body is missing")
                .status(status.value())
                .build();

        return ResponseEntity.status(status)
                .body(response);
    }


    @ExceptionHandler
    public ResponseEntity<?> httpMediaTypeNotSupportedError(NoResourceFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDto response = ErrorResponseDto.builder()
                .message(status.getReasonPhrase())
                .status(status.value())
                .build();

        return ResponseEntity.status(status)
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUnknownError(RuntimeException exception) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .message("unknown error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        log.error("unknown error: {}", exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInternalError(Exception exception) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .message("default system error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        log.error("unknown internal error: {}", exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    protected HttpStatusCode extractStatusCode(BaseException exception) {
        var httpStatusCode = MergedAnnotations.from(exception.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);
        return this.determineHttpStatus(exception, httpStatusCode);
    }

    private HttpStatusCode determineHttpStatus(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException) error).getStatusCode();
        }

        return responseStatusAnnotation.getValue("code", HttpStatus.class).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ErrorResponseDto> createResponse(String message, HttpStatusCode statusCode) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .message(message)
                .status(statusCode.value())
                .build();

        return ResponseEntity.status(statusCode)
                .body(response);
    }
}