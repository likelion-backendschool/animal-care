package com.codelion.animalcare.domain.doctorqna.dto.response;

import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.entity.Question;

import com.codelion.animalcare.domain.user.entity.Member;
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
    private Member member;

    private int likeCount;
    public QuestionResponseDto(Question entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.view = entity.getView();
        this.createdAt = entity.getCreatedAt();
        this.answerList = entity.getAnswerList();
        this.member = entity.getMember();
        this.likeCount = entity.getLikeCount();
    }
}
