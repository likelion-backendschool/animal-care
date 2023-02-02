package com.codelion.animalcare.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.service.QuestionQueryService;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@WebAppConfiguration // Spring to load a proper ServletContext with a mocked attribute entry for the ServerContainer.
@SpringBootTest
class QuestionQueryServiceTest {

    @Autowired
    private QuestionQueryService questionQueryService;

    @DisplayName("질문_조회된다")
    @Test
    void t1(){
        QuestionResponseDto question = questionQueryService.findById(1L);

        assertThat(question.getTitle().equals("title1"));
    }

}