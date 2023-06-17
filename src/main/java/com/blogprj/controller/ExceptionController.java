package com.blogprj.controller;

import com.blogprj.exception.BlogPrjException;
import com.blogprj.exception.InvalidRequest;
import com.blogprj.exception.PostNotFound;
import com.blogprj.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .validation(new HashMap<>())
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }

//    @ResponseBody
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(PostNotFound.class)
//    public ErrorResponse postNotFound(PostNotFound e) {
//        ErrorResponse response = ErrorResponse.builder()
//                .code("404")
//                .message(e.getMessage())
//                .validation(new HashMap<>())
//                .build();
//
//        return response;
//    }

    @ResponseBody
    @ExceptionHandler(BlogPrjException.class)
    public ResponseEntity<ErrorResponse> blogPrjException(BlogPrjException e) {
        int statusCode = e.getStatusCod();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

       return ResponseEntity.status(statusCode).body(body);
    }
}
