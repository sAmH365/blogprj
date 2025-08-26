package com.example.blogprj.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
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
    mockMvc.perform(
        MockMvcRequestBuilders.post("/posts")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"글 제목입니다.\", \"content\":\"글 내용입니다.\"}")
        )
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.content().string("Hello World"))
        .andDo(MockMvcResultHandlers.print());
  }

}
