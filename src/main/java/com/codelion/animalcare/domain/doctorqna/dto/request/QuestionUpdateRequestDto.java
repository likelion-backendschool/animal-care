package com.codelion.animalcare.domain.doctorqna.dto.request;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record QuestionUpdateRequestDto(
    @NotBlank(message = "제목은 필수 항목 입니다.")
    @Size(max = 200)
    String title,

    @NotBlank(message = "내용은 필수 항목 입니다.")
    String content
) {

    @Builder
    public QuestionUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
