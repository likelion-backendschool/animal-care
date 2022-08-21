package com.codelion.animalcare.domain.doctorQna.controller.dto.request;

import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionSaveRequestDto {
    private String title;
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
