package com.codelion.animalcare.domain.doctorqna.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AnswerUpdateRequestDto {

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    @Builder
    public AnswerUpdateRequestDto(String content) {
        this.content = content;
    }
}
