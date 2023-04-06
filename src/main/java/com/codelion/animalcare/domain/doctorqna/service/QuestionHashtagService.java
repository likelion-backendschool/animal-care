package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.entity.Hashtag;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.entity.QuestionHashtag;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionHashtagService {

  private final HashtagService hashtagService;

  private final QuestionHashtagRepository questionHashtagRepository;

  public void saveHashtag(Question question, List<String> tagNames) {
    tagNames.stream()
            .map(hashtag ->
                hashtagService.findByTagName(hashtag)
                              .orElseGet(() -> hashtagService.save(hashtag)))
            .forEach(hashtag -> mappedHashtagToQuestion(question, hashtag));
  }

  private Long mappedHashtagToQuestion(Question question, Hashtag hashtag) {

    return questionHashtagRepository.save(QuestionHashtag.of(question, hashtag)).getId();
  }

  public List<QuestionHashtag> findHashtagListByQuestion(Question question) {

    return questionHashtagRepository.findAllByQuestion(question);
  }
}
