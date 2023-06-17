package com.blogprj.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class BlogPrjException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public BlogPrjException(String message) {
        super(message);
    }

    public BlogPrjException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCod();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
