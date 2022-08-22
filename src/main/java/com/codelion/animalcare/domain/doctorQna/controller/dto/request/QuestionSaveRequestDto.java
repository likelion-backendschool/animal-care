package com.codelion.animalcare.domain.doctorQna.controller.dto.request;

import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionSaveRequestDto {

    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    @Size(max = 200)
    private String title;
    @NotBlank(message = "내용은 필수 입력 사항입니다.")
    private String content;


    @Builder
    public QuestionSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .build();
    }
}
