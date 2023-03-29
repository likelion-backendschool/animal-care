package com.codelion.animalcare.domain.doctorqna.dto.response;

import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.entity.Question;

import java.time.LocalDateTime;

public record AnswerResponseDto (

    Long id,
    String content,
    Question question,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public AnswerResponseDto(Answer entity) {
        this(entity.getId(), entity.getContent(), entity.getQuestion(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

}
