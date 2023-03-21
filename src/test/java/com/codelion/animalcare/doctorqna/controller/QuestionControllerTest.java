package com.codelion.animalcare.doctorqna.controller;

import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.service.QuestionCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionCommandService questionCommandService;


    @DisplayName("hi")
    @Test
    public void saveQuestion_ValidInput_ShouldSaveQuestion() throws Exception {
        QuestionSaveRequestDto dto = new QuestionSaveRequestDto("Test question", "Test content");
        Principal principal = () -> "member1@test.com";

        mockMvc.perform(post("/usr/doctor-qna/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("questionTitle", dto.title())
                        .param("content", dto.content())
                        .principal(principal))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/user/login"));

        verify(questionCommandService, times(1)).save(dto, principal);
    }

}
