package com.codelion.animalcare.domain.doctorQna.controller.dto.response;

import com.codelion.animalcare.domain.doctorQna.repository.Question;
import lombok.Getter;

@Getter
public class QuestionListResponseDto {

    private Long id;
    private String title;
    private int view;
    //private String member

    public QuestionListResponseDto(Question entity) {
        this.id = id;
        this.title = title;
        this.view = view;
    }
}
