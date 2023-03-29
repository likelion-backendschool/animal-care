package com.codelion.animalcare.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.service.AnswerQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@WebAppConfiguration // Spring to load a proper ServletContext with a mocked attribute entry for the ServerContainer.
@SpringBootTest
public class AnswerQueryServiceTest {

    @Autowired
    private AnswerQueryService answerQueryService;

    @DisplayName("답변_조회된다")
    @Test
    void t1() {
        //given --> testinit

        //when
        Answer answer = answerQueryService.findAnswerByAnswerId(1L);

        //then
        assertEquals(answer.getContent(), "answer1");
    }

    @DisplayName("답변을_작성한_의사는_권한을_가진다")
    @Test
    void t2() {

        //given
        Principal principal = () -> "doctor1@test.com";
        //when

        boolean auth = answerQueryService.answerAuthorized(1L, principal);

        //then
        assertFalse(auth);

    }

    @DisplayName("답변을_작성하지_않은_의사는_권한이_없다")
    @Test
    void t3() {

        //given
        Principal principal = () -> "doctor2@test.com";

        //when
        boolean auth = answerQueryService.answerAuthorized(1L, principal);

        //then
        assertTrue(auth);
    }

    @DisplayName("없는_답변을_조회하면_에러를_던진다")
    @Test
    void t4() {

        //given

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> answerQueryService.findAnswerByAnswerId(3L));
    }
}
