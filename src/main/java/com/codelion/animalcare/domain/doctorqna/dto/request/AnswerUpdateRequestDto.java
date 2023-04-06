package com.codelion.animalcare.domain.doctorqna.dto.request;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

public record AnswerUpdateRequestDto(
    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    String content
) {

    @Builder
    public AnswerUpdateRequestDto(String content) {
        this.content = content;
    }
}
