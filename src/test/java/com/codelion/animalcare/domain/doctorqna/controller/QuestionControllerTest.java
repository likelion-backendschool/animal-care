package com.codelion.animalcare.domain.doctorqna.controller;

import com.codelion.animalcare.domain.appointment.interceptor.HasAnimalsInterceptor;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.service.QuestionCommandService;
import com.codelion.animalcare.domain.doctorqna.service.QuestionQueryService;
import com.codelion.animalcare.domain.user.service.UserService;
import com.codelion.animalcare.global.config.MvcConfig;
import com.codelion.animalcare.webrtc.controller.WebrtcController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = QuestionController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MvcConfig.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = HasAnimalsInterceptor.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebrtcController.class)})
public class QuestionControllerTest {

    @MockBean
    private QuestionCommandService questionCommandService;

    @MockBean
    private QuestionQueryService questionQueryService;

    @MockBean
    private UserService userService;

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Principal principal;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("권한이_없으면_Redirection_된다")
    @Test
    void saveForm_Fail() throws Exception {

        String viewName = questionController.saveForm(new QuestionSaveRequestDto("title", "content"));

        mockMvc.perform(get("/usr/doctor-qna/write"))
                .andExpect(status().is3xxRedirection());


    }

    @DisplayName("QuestionController_질문작성폼_보여진다")
    @WithMockUser(roles="MEMBER")
    @Test
    void saveForm_Success() throws Exception {

        String viewName = questionController.saveForm(new QuestionSaveRequestDto("title", "content"));

        mockMvc.perform(get("/usr/doctor-qna/write"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));

    }

    @Test
    @WithMockUser(roles="MEMBER")
    void saveTest_withValidRequestDto() throws Exception {
        // given
        QuestionSaveRequestDto requestDto = new QuestionSaveRequestDto("title", "content");

        // when
        mockMvc.perform(post("/usr/doctor-qna/write")
                        .param("title", requestDto.title())
                        .param("content", requestDto.content()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/usr/doctor-qna"))
                .andExpect(flash().attributeExists("message"));

        // then
        verify(questionCommandService, times(1)).save(requestDto, any(Principal.class));
    }

    @Test
    @WithMockUser(roles="MEMBER")
    void saveTest_withInvalidRequestDto() throws Exception {
        // given
        QuestionSaveRequestDto requestDto = new QuestionSaveRequestDto("", "");

        // when
        mockMvc.perform(post("/usr/doctor-qna/write")
                        .param("title", requestDto.title())
                        .param("content", requestDto.content()))
                .andExpect(status().isOk())
                .andExpect(view().name("doctorqna/doctorQnaQuestionForm"));

        // then
        verifyNoInteractions(questionCommandService);
    }
}

