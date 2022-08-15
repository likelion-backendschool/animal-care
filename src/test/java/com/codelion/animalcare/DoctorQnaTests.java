package com.codelion.animalcare;

import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.AnswerRepository;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import com.codelion.animalcare.domain.doctorQna.repository.QuestionRepository;
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


@SpringBootTest
public class DoctorQnaTests {


    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;


    @AfterEach
    public void tearDown() {
        questionRepository.deleteAll();
        answerRepository.deleteAll();
    }

    @Test
    public void Questions_등록된다() {

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
    public void Questions_수정된다() {
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

        System.out.println(">>>>>>>>>> createDate=" + question.getCreatedDate()+", modifiedDate=" + question.getModifiedDate());

        assertThat(question.getCreatedDate()).isAfter(now);
        assertThat(question.getModifiedDate()).isAfter(now);
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

        String title2 = "답변입니다.";
        String content2 = "답변이 되었나요?";

        answerRepository.save(Answer.builder()
                .title(title2)
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
                .title(title2)
                .content(content2)
                .question(question)
                .build());

        Answer answer = answerRepository.findById(1L).get();

        question.addAnswer(answerRepository.findById(1L).get());

        answer.update("답변이 수정되었습니다.", "답변이 수정되었나요?");

        List<Answer> answerList = question.getAnswerList();

        answer = answerList.get(0);
        System.out.println("%s , %s".formatted(answer.getTitle(), answer.getContent()));
        assertEquals("답변이 수정되었습니다.", answer.getTitle());
        assertEquals("답변이 수정되었나요?", answer.getContent());
    }




}
