package com.codelion.animalcare.domain.doctorQna.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerUpdateRequestDto {

    private String title;

    private String content;

    @Builder
    public AnswerUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
