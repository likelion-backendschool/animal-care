package com.codelion.animalcare.domain.doctorQna.controller.dto.response;

import com.codelion.animalcare.domain.doctorQna.repository.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionListResponseDto {

    private Long id;
    private String title;

    private LocalDateTime createdAt;
    private int view;

    /*TODO : private String member
             private Integer like */

    public QuestionListResponseDto(Question entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.createdAt = entity.getCreatedAt();
        this.view = entity.getView();
    }
}
