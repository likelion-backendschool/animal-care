package com.codelion.animalcare.domain.doctorqna.dto.response;

import com.codelion.animalcare.domain.doctorqna.repository.Answer;
import com.codelion.animalcare.domain.doctorqna.repository.Question;
import com.codelion.animalcare.domain.user.entity.Patient;
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
    /*TODO :  private Integer like */
    private Patient patient;

    public QuestionResponseDto(Question entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.view = entity.getView();
        this.createdAt = entity.getCreatedAt();
        this.answerList = entity.getAnswerList();
        this.patient = entity.getPatient();
    }
}
