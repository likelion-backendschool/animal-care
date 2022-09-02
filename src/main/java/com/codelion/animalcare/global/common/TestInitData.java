package com.codelion.animalcare.global.common;

import com.codelion.animalcare.domain.doctorqna.repository.Question;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
@Profile("test")
public class TestInitData {

    @Bean
    CommandLineRunner init(QuestionRepository questionRepository, MemberRepository memberRepository) {
        return args -> {

            Member member1 = memberRepository.save(Member.builder()

                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .genderId(0)
                    .email("member1@naver.com")
                    .password("123")
                    .name("신상원")
                    .phoneNum("01012345678")
                    .auth("ROLE_USER")
                    .build());


            memberRepository.save(member1);


            Question question1 = questionRepository.save(Question.builder()
                    .title("title1")
                    .content("content1")
                    .member(member1)
                    .build());

            questionRepository.save(question1);
        };
    }
}
