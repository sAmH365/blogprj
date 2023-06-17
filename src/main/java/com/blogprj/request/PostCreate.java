package com.blogprj.request;

import com.blogprj.exception.InvalidRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ToString
@Setter
@Getter
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.")
    public String title;

    @NotBlank(message = "컨텐츠를 입력해주세요.")
    public String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void validate() {
        if (title.contains("wrong word")) {
            throw new InvalidRequest("title", "제목에 worng word를 포함 할 수 없습니다.");
        }
    }
}
