package com.codelion.animalcare.domain.doctorqna.dto.request;

import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.user.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record QuestionSaveRequestDto(
        @NotBlank(message = "제목은 필수 입력 사항입니다.")
        @Size(max = 200, message = "제목이 너무 길어요.")
        String title,

        @NotBlank(message = "내용은 필수 입력 사항입니다.") String content
) {
    public Question toEntity(Member member) {
        return Question.builder()
                .title(title())
                .content(content())
                .member(member)
                .build();
    }
}
