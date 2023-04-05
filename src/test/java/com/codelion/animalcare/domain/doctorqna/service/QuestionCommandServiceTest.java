package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorqna.entity.Hashtag;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.entity.QuestionHashtag;
import com.codelion.animalcare.domain.user.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private QuestionHashtagQueryService questionHashtagQueryService;

    @DisplayName("질문_작성된다")
    @Test
    void t1() {
        //given
        QuestionSaveRequestDto question = new QuestionSaveRequestDto("질문이 있습니다.", "테스트 코드는 어떻게 잘 짜나요?", null);
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

    @DisplayName("해시태그_저장된다")
    @Test
    void t5() {
        //given

        Hashtag javaTag = Hashtag.builder().tagName("JAVA").build();
        Hashtag testTag = Hashtag.builder().tagName("TEST").build();
        Set<Hashtag> hashtags =new HashSet<>();
        hashtags.add(javaTag);
        hashtags.add(testTag);
        QuestionSaveRequestDto question = new QuestionSaveRequestDto("질문이 있습니다.", "테스트 코드는 어떻게 잘 짜나요?", hashtags);
        Principal principal = () -> "member1@test.com";

        //when
        questionCommandService.save(question, principal);
        //Testinitdata -> 3 question
        Question savedQuestion = questionQueryService.findQuestionByQuestionId(4L);
        List<QuestionHashtag> hashtagList = questionHashtagQueryService.findHashtagListByQuestion(savedQuestion);

        for(QuestionHashtag questionHashtag : hashtagList) {
            System.out.println(questionHashtag.getHashtag().getTagName());
        }

        hashtagList.forEach(hashtag -> System.out.println(hashtag.getHashtag().getTagName()));

        assertEquals(hashtagList.size(), 2);
    }

}

