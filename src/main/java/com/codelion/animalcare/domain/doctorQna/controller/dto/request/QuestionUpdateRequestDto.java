package com.codelion.animalcare.domain.doctorQna.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class QuestionUpdateRequestDto {
    @NotBlank(message = "제목은 필수 항목 입니다.")
    @Size(max = 200)
    private String title;

    @NotBlank(message = "내용은 필수 항목 입니다.")
    private String content;

    @Builder
    public QuestionUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
