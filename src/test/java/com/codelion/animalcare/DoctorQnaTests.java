package com.codelion.animalcare;

import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionListResponseDto;
import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.AnswerRepository;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import com.codelion.animalcare.domain.doctorQna.repository.QuestionRepository;
import com.codelion.animalcare.domain.doctorQna.service.AnswerService;
import com.codelion.animalcare.domain.doctorQna.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class DoctorQnaTests {


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;


/*    @AfterEach
    public void tearDown() {
        questionRepository.deleteAll();
        answerRepository.deleteAll();
    }*/

    @Test
    public void tearUp() {
        String title = "test title";
        String content = "test content";

        questionRepository.save(Question.builder()
                .title(title)
                .content(content)
                .build());

        String title2 = "test title2";
        String content2 = "test content2";

        questionRepository.save(Question.builder()
                .title(title2)
                .content(content2)
                .build());
        

    }


    @Test
    public void Question_등록된다() {

        String title = "test title";
        String content = "test content";

        questionRepository.save(Question.builder()
                .title(title)
                .content(content)
                .build());

        List<Question> questionsList = questionRepository.findAll();

        Question question = questionsList.get(0);
        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getContent()).isEqualTo(content);

    }

    @Test
    public void Question_수정된다() {
        String title = "test title";
        String content = "test content";

        questionRepository.save(Question.builder()
                .title(title)
                .content(content)
                .build());

        List<Question> questionsList = questionRepository.findAll();

        Question question = questionsList.get(0);

        String title2 = "test title2";
        String content2 = "test content2";

        question.update(title2, content2);

        assertThat(question.getTitle()).isEqualTo(title2);
        assertThat(question.getContent()).isEqualTo(content2);

    }

    @Test
    void findById_작동() {
        String title = "test title";
        String content = "test content";

        questionRepository.save(Question.builder()
                .title(title)
                .content(content)
                .build());

        String title2 = "test title2";
        String content2 = "test content2";

        questionRepository.save(Question.builder()
                .title(title2)
                .content(content2)
                .build());


        Optional<Question> oq = this.questionRepository.findById(2L);
        Question question = oq.get();

        assertThat(question.getTitle()).isEqualTo(title2);
        assertThat(question.getContent()).isEqualTo(content2);

    }

    @Test
    void BaseTimeEntity_등록된다() {
        LocalDateTime now = LocalDateTime.of(2022,8,15,0,0,0);
        questionRepository.save(Question.builder()
                .title("title")
                .content("content")
                .build());

        // when
        List<Question> postsList = questionRepository.findAll();

        // then
        Question question = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate=" + question.getCreatedAt()+", modifiedDate=" + question.getUpdatedAt());

        assertThat(question.getCreatedAt()).isAfter(now);
        assertThat(question.getUpdatedAt()).isAfter(now);
    }

    @Test
    @Transactional
    @Rollback(false)
    void Answer_등록된다(){
        String title = "test title";
        String content = "test content";

        questionRepository.save(Question.builder()
                .title(title)
                .content(content)
                .answerList(new ArrayList<>())
                .build());

        Question question = questionRepository.findById(Long.valueOf(1)).get();

        String content2 = "답변이 되었나요?";

        answerRepository.save(Answer.builder()
                .content(content2)
                .question(question)
                .build());

        question.addAnswer(answerRepository.findById(1L).get());
        List<Answer> answerList = question.getAnswerList();

        System.out.println(answerList.size());


    }
    @Transactional
    @Rollback(false)
    @Test
    void Answer_수정된다() {
        String title = "test title";
        String content = "test content";

        questionRepository.save(Question.builder()
                .title(title)
                .content(content)
                .answerList(new ArrayList<>())
                .build());

        Question question = questionRepository.findById(Long.valueOf(1)).get();

        String title2 = "답변입니다.";
        String content2 = "답변이 되었나요?";

        answerRepository.save(Answer.builder()
                .content(content2)
                .question(question)
                .build());

        Answer answer = answerRepository.findById(1L).get();

        question.addAnswer(answerRepository.findById(1L).get());

        answer.update("답변이 수정되었습니다.");

        List<Answer> answerList = question.getAnswerList();

        answer = answerList.get(0);
        System.out.println("%s , %s".formatted(answer.getContent()));
        assertEquals("답변이 수정되었나요?", answer.getContent());
    }

    @Test
    public void Question_삭제된다() {

        String title = "test title";
        String content = "test content";

        questionRepository.save(Question.builder()
                .title(title)
                .content(content)
                .build());

        Optional<Question> question = questionRepository.findById(1L);

        assertTrue(question.isPresent());

        if(question.isPresent()){
            System.out.println("데이터 존재 : "+ question.get().getTitle());
        }else{
            System.out.println("데이터 없음");
        }

        question.ifPresent(selectQuestion->{
            questionService.delete(selectQuestion.getId());
        });

        Optional<Question> deleteQuestion = questionRepository.findById(1L);

        assertFalse(deleteQuestion.isPresent());

        if(deleteQuestion.isPresent()){
            System.out.println("데이터 존재 : "+deleteQuestion.get());
        }else{
            System.out.println("데이터 없음");
        }
    }

    @Test
    public void test_1() {
        QuestionSaveRequestDto questionSaveRequestDto = new QuestionSaveRequestDto("title", "content");

        questionService.save(questionSaveRequestDto);

        Question question = questionRepository.findById(1L).orElseThrow();

        List<Answer> answerList = question.getAnswerList();

        System.out.println(answerList.size());




    }



}
