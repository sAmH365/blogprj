package com.example.blogprj.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class PostControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("/posts 요청시 Hello Wolrd를 출력한다.")
  void test() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.content().string("Hello World"))
        .andDo(MockMvcResultHandlers.print());
  }

}
