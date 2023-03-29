package com.codelion.animalcare.doctorqna;
/*
import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.response.AnswerResponseDto;
import com.codelion.animalcare.domain.doctorqna.dto.response.QuestionListResponseDto;
import com.codelion.animalcare.domain.doctorqna.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorqna.repository.AnswerRepository;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
import com.codelion.animalcare.domain.doctorqna.service.AnswerService;
import com.codelion.animalcare.domain.doctorqna.service.QuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@SpringBootTest
public class DoctorQnaTests {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

   */
/* public void init() {
        //1, 2번 질문 답변 생성
        questionService.save(QuestionSaveRequestDto.builder()
                .title("title1")
                .content("content1")
                .build());

        questionService.save(QuestionSaveRequestDto.builder()
                .title("title2")
                .content("content2")
                .build());

        answerService.save(1L, AnswerSaveRequestDto.builder()
                .content("answer1")
                .build());

        answerService.save(2L, AnswerSaveRequestDto.builder()
                .content("answer2")
                .build());
    }*//*




    @BeforeEach
    public void tearDown() {
        questionRepository.deleteAll();
        answerRepository.deleteAll();
        questionRepository.truncate();
        answerRepository.truncate();
        */
/*init();*//*

    }



    @Test
    public void 질문_조회된다() {
        List<QuestionListResponseDto> allList = questionService.findAllDesc();

        assertThat(allList.size()).isEqualTo(2);

        String firstTitle = allList.get(0).getTitle();


        assertThat(firstTitle).isEqualTo("title2");
    }


    @Test
    public void 질문_작성된다() {


        List<QuestionListResponseDto> allList = questionService.findAllDesc();

        String firstTitle = allList.get(0).getTitle();

        assertThat(firstTitle).isEqualTo("title3");


    }

    @Test
    public void 질문_수정된다() {

        String modifyTitle = "modify1";
        String modifyContent = "modify2";

        questionService.update(1L, QuestionUpdateRequestDto.builder()
                .title(modifyTitle)
                .content(modifyContent)
                .build());


        QuestionResponseDto question = questionService.findById(1L);

        assertThat(question.getTitle()).isEqualTo("modify1");
        assertThat(question.getContent()).isEqualTo("modify2");


    }

    @Test
    public void 질문_삭제된다() {

        questionService.delete(1L);

        List<QuestionListResponseDto> allList = questionService.findAllDesc();

        assertThat(allList.size()).isEqualTo(1);


    }

    @Test
    public void 답변_조회된다(){
        AnswerResponseDto answerResponseDto = answerService.findById(1L);

        assertThat(answerResponseDto.getContent()).isEqualTo("answer1");

        answerResponseDto = answerService.findById(2L);

        assertThat(answerResponseDto.getContent()).isEqualTo("answer2");
    }

*/
/*    @Test
    *//*
*/
/*public void 답변_작성된다(){
        answerService.save(1L, AnswerSaveRequestDto.builder()
                .content("saveContent!")
                .build());*//*
*/
/*

        AnswerResponseDto answerResponseDto = answerService.findById(3L);
        String testContent = answerResponseDto.getContent();

        *//*
*/
/*assertThat(testContent).isEqualTo("saveContent!");*//*
*/
/*

    }*//*


    @Test
    public void 답변_수정된다(){
        String modifyContent = "modifyAnswer";

        answerService.update(1L, 1L, AnswerUpdateRequestDto.builder()
                .content(modifyContent)
                .build());



        AnswerResponseDto answer = answerService.findById(1L);

        assertThat(answer.getContent()).isEqualTo("modifyAnswer");
    }

    @Test
    public void 답변_삭제된다(){

        answerService.delete(1L, 1L);

        Assertions.assertThatThrownBy(() -> answerService.findById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("답변이 존재하지 않습니다.");

    }



}
*/
