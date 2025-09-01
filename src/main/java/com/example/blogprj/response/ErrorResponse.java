package com.example.blogprj.response;


import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

  private final int code;
  private final String message;
  private final Map<String, String> validation;

  public void addValidation(String field, String errorMessage) {
    this.validation.put(field, errorMessage);
  }
}
