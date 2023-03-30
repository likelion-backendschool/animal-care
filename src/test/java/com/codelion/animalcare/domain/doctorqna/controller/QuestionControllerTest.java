package com.codelion.animalcare.domain.doctorqna.controller;

import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.service.QuestionCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BindingResult;

import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class QuestionControllerTest {

    @Mock
    private QuestionCommandService questionCommandService;

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Principal principal;

    @DisplayName("QuestionController_질문등록_성공")
    @Test
    void save_questionSaveRequestDtoIsValid_success() throws Exception {
        // given
        QuestionSaveRequestDto requestDto = new QuestionSaveRequestDto("title", "content");

        // when
        String viewName = questionController.save(requestDto, bindingResult, principal);

        // then
        assertThat(viewName).isEqualTo("redirect:/usr/doctor-qna");
        verify(questionCommandService).save(requestDto, principal);
    }

    @DisplayName("QuestionController_질문등록_실패")
    @Test
    void save_questionSaveRequestDtoIsInvalid_failure() throws Exception {
        // given
        QuestionSaveRequestDto requestDto = new QuestionSaveRequestDto("", "");

        when(bindingResult.hasErrors()).thenReturn(true);

        // when
        String viewName = questionController.save(requestDto, bindingResult, principal);

        // then
        assertThat(viewName).isEqualTo("doctorqna/doctorQnaQuestionForm");
        verifyNoInteractions(questionCommandService);
    }
}

