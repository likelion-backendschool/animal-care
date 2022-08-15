package com.codelion.animalcare;

import com.codelion.animalcare.domain.doctorQna.repository.Question;
import com.codelion.animalcare.domain.doctorQna.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DoctorQnaTests {


    @Autowired
    private QuestionRepository questionRepository;

    @AfterEach
    public void tearDown() {
        questionRepository.deleteAll();
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

        Question question = questionRepository.findById(Long.valueOf(2)).get();

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





}
