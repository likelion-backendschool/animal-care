package com.codelion.animalcare.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
import com.codelion.animalcare.domain.doctorqna.service.AnswerCommandService;
import com.codelion.animalcare.domain.doctorqna.service.AnswerQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@WebAppConfiguration
@Transactional
@SpringBootTest
public class AnswerCommandServiceTest {

    @Autowired
    private AnswerCommandService answerCommandService;
    @Autowired
    private AnswerQueryService answerQueryService;
    @Autowired
    private QuestionRepository questionRepository;

    @DisplayName("1번_게시글에_답변_저장된다")
    @Test
    void t1() {

        //given
        Principal principal = () -> "doctor1@test.com";
        Question question = questionRepository.findById(1L).orElseThrow();
        AnswerSaveRequestDto answerSaveRequestDto = AnswerSaveRequestDto.builder()
                .content("열심히 공부하세요.")
                .question(question)
                .build();
        //when
        answerCommandService.save(1L, answerSaveRequestDto, principal);
        List<Answer> answerList = question.getAnswerList();
        Answer testAnswer = answerList.get(answerList.size()-1);

        //then
        assertEquals(answerList.size(), 2);
        assertEquals(testAnswer.getContent(), "열심히 공부하세요.");

    }

    @DisplayName("답변_수정된다")
    @Test
    void t2() {

        //given
        AnswerUpdateRequestDto answerUpdateRequestDto = AnswerUpdateRequestDto.builder()
                .content("수정합니다")
                .build();

        //when
        answerCommandService.update(1L, answerUpdateRequestDto);
        Answer answer = answerQueryService.findAnswerByAnswerId(1L);
        //then
        assertEquals(answer.getContent(), "수정합니다");
    }

    @DisplayName("답변_삭제된다")
    @Test
    void t3() {

        //given -> testinit

        //when
        answerCommandService.delete(1L);
        //then
        assertThrows(IllegalArgumentException.class, () -> answerQueryService.findAnswerByAnswerId(1L));
    }
}
