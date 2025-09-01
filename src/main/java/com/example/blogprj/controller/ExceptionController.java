package com.example.blogprj.controller;

import com.example.blogprj.response.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
    ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다.");

    for(FieldError fieldError : e.getFieldErrors()) {
      response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return response;
  }

}
