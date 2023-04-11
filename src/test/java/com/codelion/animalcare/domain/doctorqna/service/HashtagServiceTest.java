package com.codelion.animalcare.domain.doctorqna.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.codelion.animalcare.domain.doctorqna.entity.Hashtag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@WebAppConfiguration
@Transactional
@SpringBootTest
class HashtagServiceTest {

    @Autowired
    private QuestionQueryService questionQueryService;
    @Autowired
    private HashtagService hashtagService;

    @Test
    @DisplayName("태그_저장된다 - 성공")
    void save() {

        //given
        String tagName = "강아지";
        hashtagService.save(tagName);

        //when
        Hashtag hashtag = hashtagService.findByTagName(tagName)
                                        .orElse(null);

        //then
        assertNotNull(hashtag);
        assertEquals(hashtag.getTagName(), "강아지");

    }
}