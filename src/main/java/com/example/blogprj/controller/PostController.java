package com.example.blogprj.controller;

import com.example.blogprj.request.PostCreate;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PostController {

  @PostMapping("/posts")
  public String post(@RequestBody PostCreate params) {
    log.info("params : {}", params.toString());
    return "Hello World";
  }

}
