package com.codelion.animalcare.domain.doctorqna.dto.response;

import com.codelion.animalcare.domain.doctorqna.entity.Question;

import com.codelion.animalcare.domain.user.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionListResponseDto {

    private Long id;
    private String title;

    private LocalDateTime createdAt;
    private int view;

    private int likeCount;
    private Member member;

    /*TODO : private String member
             private Integer like */

    public QuestionListResponseDto(Question entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.createdAt = entity.getCreatedAt();
        this.view = entity.getView();
        this.likeCount = entity.getLikeCount();
        this.member = entity.getMember();
    }
}
