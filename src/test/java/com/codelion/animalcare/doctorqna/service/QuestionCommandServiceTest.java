package com.codelion.animalcare.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.service.QuestionCommandService;
import com.codelion.animalcare.domain.doctorqna.service.QuestionQueryService;
import com.codelion.animalcare.domain.user.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@WebAppConfiguration // Spring to load a proper ServletContext with a mocked attribute entry for the ServerContainer.
@Transactional
@SpringBootTest
public class QuestionCommandServiceTest {

    @Autowired
    private QuestionCommandService questionCommandService;
    @Autowired
    private QuestionQueryService questionQueryService;
    @Autowired
    private MemberService memberService;

    @DisplayName("질문_작성된다")
    @Test
    void t1() {
        //given
        QuestionSaveRequestDto question = new QuestionSaveRequestDto("질문이 있습니다.", "테스트 코드는 어떻게 잘 짜나요?");
        Principal principal = () -> "member1@test.com";

        //when
        questionCommandService.save(question, principal);
        //Testinitdata -> 3 question
        QuestionResponseDto savedQuestion = questionQueryService.findById(4L);
        //then
        System.out.println(savedQuestion);
        assertEquals(savedQuestion.title(), "질문이 있습니다.");
        assertEquals(savedQuestion.content(), "테스트 코드는 어떻게 잘 짜나요?");
        assertEquals(savedQuestion.member().getEmail(), "member1@test.com");
    }

    @DisplayName("질문_수정된다")
    @Test
    void t2() {
        //given
        QuestionUpdateRequestDto updateRequestDto = QuestionUpdateRequestDto.builder()
                .title("수정합니다.")
                .content("수정이 잘 되나요?")
                .build();

        //when
        questionCommandService.update(1L, updateRequestDto);
        Question question = questionQueryService.findQuestionByQuestionId(1L);
        //then
        assertEquals(question.getTitle(), "수정합니다.");
        assertEquals(question.getContent(),"수정이 잘 되나요?" );
        assertEquals(question.getMember().getEmail(), "member1@test.com");

    }

    @DisplayName("조회수_올라간다")
    @Test
    void t3() {
       //given
        Question question = questionQueryService.findQuestionByQuestionId(1L);
        //when
        questionCommandService.updateView(1L);

        int expectedView = questionQueryService.findQuestionByQuestionId(1L).getView();

        assertEquals(1, expectedView);


    }

    @DisplayName("게시물_삭제된다")
    @Test
    void t4() {
        //given --> t1() create

        //when
        questionCommandService.delete(3L);
        //given
        assertThrows(IllegalArgumentException.class, () -> questionQueryService.findQuestionByQuestionId(3L));
    }
}

