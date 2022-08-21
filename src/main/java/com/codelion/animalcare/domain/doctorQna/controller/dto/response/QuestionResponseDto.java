package com.codelion.animalcare.domain.doctorQna.controller.dto.response;

import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QuestionResponseDto {

    private Long id;
    private String title;
    private String content;

    private LocalDateTime createdAt;
    private int view;

    private List<Answer> answerList;
    //private String member

    public QuestionResponseDto(Question entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.view = entity.getView();
        this.createdAt = entity.getCreatedAt();
        this.answerList = getAnswerList();
    }
}
