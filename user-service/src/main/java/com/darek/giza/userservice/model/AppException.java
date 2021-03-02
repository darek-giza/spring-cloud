package com.darek.giza.userservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AppException extends RuntimeException {
    private int httpStatus;
    private String errorMessage;
    private Exception cause;

    @Builder
    public AppException(int httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public AppException(int httpStatus, String errorMessage, Exception cause) {
        super(errorMessage, cause);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.cause = cause;
    }

    public static AppException notFound(String errorMessage) {
        return new AppException(HttpStatus.NOT_FOUND.value(), errorMessage);
    }

    public static AppException badRequest(String errorMessage) {
        return new AppException(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    public static AppException internalServerError(String errorMessage, Exception cause) {
        return new AppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, cause);
    }

    public static AppException internalServerError(String errorMessage) {
        return new AppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
    }
}
