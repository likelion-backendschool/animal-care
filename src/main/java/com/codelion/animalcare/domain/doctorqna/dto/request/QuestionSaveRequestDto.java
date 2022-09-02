package com.codelion.animalcare.domain.doctorqna.dto.request;

import com.codelion.animalcare.domain.doctorqna.repository.Question;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class QuestionSaveRequestDto {

    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    @Size(max = 40, message = "제목이 너무 길어요.")
    private String title;
    @NotBlank(message = "내용은 필수 입력 사항입니다.")
    private String content;


    @Builder
    public QuestionSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Question toEntity(Member member) {
        return Question.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
