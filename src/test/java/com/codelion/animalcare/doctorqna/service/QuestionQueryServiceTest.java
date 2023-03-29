package com.codelion.animalcare.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.service.QuestionCommandService;
import com.codelion.animalcare.domain.doctorqna.service.QuestionQueryService;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@WebAppConfiguration // Spring to load a proper ServletContext with a mocked attribute entry for the ServerContainer.
@SpringBootTest
class QuestionQueryServiceTest {

    @Autowired
    private QuestionQueryService questionQueryService;
    @Autowired
    private QuestionCommandService questionCommandService;
    @Autowired
    private MemberService memberService;

    @DisplayName("질문_조회된다")
    @Test
    void t1(){
        //given -> testinit

        //when
        QuestionResponseDto question = questionQueryService.findById(1L);

        //then
        assertEquals(question.title(), "title1");
    }

    @DisplayName("게시물_작성자는_권한을_갖는다")
    @Transactional(readOnly = true)
    @Test
    void t2() {
        //given
        Member member = memberService.findById(1L).orElseThrow();

        //when
        Principal principal = () -> "member1@test.com";

        //then
        assertFalse(questionQueryService.questionAuthorized(1L, principal));

    }

    @DisplayName("게시물_작성자가_아니면_권한을_가지지_않는다")
    @Transactional(readOnly = true)
    @Test
    void t3() {
        //given
        Member member = memberService.findById(2L).orElseThrow();

        //when
        Principal principal = () -> "member1@test.com";

        //then
        assertFalse(questionQueryService.questionAuthorized(1L, principal));
    }

    @DisplayName("좋아요_처음_누르면_저장하고_true_리턴한다")
    @Transactional
    @Test
    void t4() {
        //given
        Member member = memberService.findById(1L).orElseThrow();

        //when
        questionCommandService.saveLike(1L, member);
        boolean like_flag = questionQueryService.findLike(1L, member);
        //then
        assertTrue(like_flag);
    }

    @DisplayName("좋아요_두번_누르면_삭제_false_리턴한다")
    @Transactional
    @Test
    void t5() {
        //given
        Member member = memberService.findById(1L).orElseThrow();

        //when
        questionCommandService.saveLike(1L, member);
        questionCommandService.saveLike(1L, member);
        boolean like_flag = questionQueryService.findLike(1L, member);
        //then
        assertFalse(like_flag);
    }

    @DisplayName("페이징_동작한다")
    @Transactional(readOnly = true)
    @Test
    void t6() {
        //given
        int page = 0;
        String type = "title";
        String kw = "spring";
        //when
        Page<Question> questions = questionQueryService.findAll(page, type, kw);
        //then
        assertNotNull(questions);
        assertEquals(10, questions.getSize());
        assertEquals("How to learn Spring boot?", questions.getContent().get(0).getTitle());
        assertEquals("DESC", Objects.requireNonNull(questions.getSort().getOrderFor("createdAt")).getDirection().toString());
    }

}