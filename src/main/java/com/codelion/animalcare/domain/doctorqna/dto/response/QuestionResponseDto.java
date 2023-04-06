package com.codelion.animalcare.domain.doctorqna.dto.response;

import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.user.entity.Member;

import java.time.LocalDateTime;
import java.util.List;
//TODO : 여기에, hashtags를 추가해주는 것이 db쿼리를 줄일 수 있을 것 같은데, 그걸 위해 question에 hashtags를 멤버변수로 가지는 것이 괜찮을 지 생각해보기
public record QuestionResponseDto(
    Long id,
    String title,
    String content,
    LocalDateTime createdAt,
    int view,
    List<Answer> answerList,
    Member member,
    int likeCount
    
) {

    public QuestionResponseDto(Question entity) {
        this(entity.getId(), entity.getTitle(), entity.getContent(), entity.getCreatedAt(),
            entity.getView(),
            entity.getAnswerList(), entity.getMember(), entity.getLikeCount());
    }

}
