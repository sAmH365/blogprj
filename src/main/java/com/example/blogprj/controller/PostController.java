package com.example.blogprj.controller;

import com.example.blogprj.request.PostCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PostController {

  @PostMapping("/posts")
  public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) {
    if (result.hasErrors()) {
      List<FieldError> fieldErrors = result.getFieldErrors();
      FieldError firstFieldError = fieldErrors.getFirst();
      String fieldName = firstFieldError.getField();
      String errorMessage = firstFieldError.getDefaultMessage();

      Map<String, String> error = new HashMap<>();
      error.put(fieldName, errorMessage);
      return error;
    }

    return Map.of();
  }

}
