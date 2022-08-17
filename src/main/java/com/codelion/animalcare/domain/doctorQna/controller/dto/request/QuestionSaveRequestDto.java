package com.codelion.animalcare.domain.doctorQna.controller.dto.request;

import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionSaveRequestDto {

    private String title;
    private String content;
    private int view;
    private List<Answer> answerList;

    @Builder
    public QuestionSaveRequestDto(String title, String content, int view, List<Answer> answerList) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.answerList = answerList;
    }

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .view(view)
                .answerList(answerList)
                .build();
    }
}
