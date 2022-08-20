package com.codelion.animalcare.domain.doctorQna.controller.dto.request;

import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class AnswerSaveRequestDto {

    private String title;
    private String content;
    @Setter
    private Question question;

    @Builder
    public AnswerSaveRequestDto(String title, String content, Question question){
        this.title = title;
        this.content = content;
        this.question = question;
    }

    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .question(question)
                .build();
    }
}
