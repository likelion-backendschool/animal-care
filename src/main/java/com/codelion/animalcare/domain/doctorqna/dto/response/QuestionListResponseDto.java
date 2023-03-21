package com.codelion.animalcare.domain.doctorqna.dto.response;

import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.user.entity.Member;

import java.time.LocalDateTime;

public record QuestionListResponseDto (
    Long id,
    String title,
    LocalDateTime createdAt,
    int view,
    int likeCount,
    Member member

) {
    public QuestionListResponseDto(Question entity) {
        this(entity.getId(), entity.getTitle(), entity.getCreatedAt(), entity.getView(), entity.getLikeCount(), entity.getMember());
    }
}
