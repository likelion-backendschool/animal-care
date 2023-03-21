package com.codelion.animalcare.domain.doctorqna.dto.response;

import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.user.entity.Member;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionResponseDto (

    Long id,
    String title,
    String content,
    LocalDateTime createdAt,
    int view,
    List<Answer> answerList,
    Member member,
    int likeCount
) {
    public QuestionResponseDto(Question entity){
        this(entity.getId(), entity.getTitle(), entity.getContent(), entity.getCreatedAt(), entity.getView(),
                entity.getAnswerList(), entity.getMember(), entity.getLikeCount());
    }

}
