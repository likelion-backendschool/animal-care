package com.codelion.animalcare.domain.doctorQna.controller.dto.response;

import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import lombok.Getter;

import java.util.List;

@Getter
public class QuestionResponseDto {

    private Long id;
    private String title;
    private String content;
    private int view;

    private List<Answer> answerList;
    //private String member

    public QuestionResponseDto(Question entity){
        this.id = entity.getQuestionId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.view = entity.getView();
        this.answerList = getAnswerList();
    }
}
