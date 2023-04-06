package com.codelion.animalcare.domain.doctorqna.repository;

import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.entity.QuestionHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionHashtagRepository extends JpaRepository<QuestionHashtag, Long> {

    List<QuestionHashtag> findAllByQuestion(Question question);
}
