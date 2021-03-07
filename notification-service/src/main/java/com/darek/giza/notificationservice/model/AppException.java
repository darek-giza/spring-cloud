package com.darek.giza.notificationservice.model;

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
    public AppException(int httpStatus, String errorMessage, Exception cause) {
        super(errorMessage, cause);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.cause = cause;
    }

    public static AppException internalServerError(String errorMessage, Exception cause) {
        return new AppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, cause);
    }
}
