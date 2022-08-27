package com.codelion.animalcare.domain.doctorqna.dto.request;

import com.codelion.animalcare.domain.doctorqna.repository.Answer;
import com.codelion.animalcare.domain.doctorqna.repository.Question;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class AnswerSaveRequestDto {

    @NotEmpty(message = "내용은 필수 입력 항목입니다.")
    private String content;
    @Setter
    private Question question;

    @Builder
    public AnswerSaveRequestDto(String content, Question question){
        this.content = content;
        this.question = question;
    }

    public Answer toEntity(Member member) {
        return Answer.builder()
                .content(content)
                .question(question)
                .member(member)
                .build();
    }
}
