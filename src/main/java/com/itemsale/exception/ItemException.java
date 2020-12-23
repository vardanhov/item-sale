package com.itemsale.exception;

import org.springframework.http.HttpStatus;

public class ItemException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;


    public ItemException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
