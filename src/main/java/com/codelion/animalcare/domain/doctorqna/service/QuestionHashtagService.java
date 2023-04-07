package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.entity.Hashtag;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.entity.QuestionHashtag;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionHashtagRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionHashtagService {

  private final HashtagService hashtagService;

  private final QuestionHashtagRepository questionHashtagRepository;

  public void saveHashtag(Question question, List<String> tagNames) {

    if(tagNames.size() == 0) return;

    tagNames.stream()
            .map(hashtag ->
                hashtagService.findByTagName(hashtag)
                              .orElseGet(() -> hashtagService.save(hashtag)))
            .forEach(hashtag -> mapHashtagToQuestion(question, hashtag));
  }

  private Long mapHashtagToQuestion(Question question, Hashtag hashtag) {

    return questionHashtagRepository.save(new QuestionHashtag(question, hashtag)).getId();
  }

  public List<QuestionHashtag> findHashtagListByQuestion(Question question) {

    return questionHashtagRepository.findAllByQuestion(question);
  }

  public Page<Question> findAllByHashtag(int page, String hashtag) {
    List<Sort.Order> sortsList = new ArrayList<>();
    sortsList.add(Sort.Order.desc("createdAt"));

    Pageable pageable = PageRequest.of(page, 10, Sort.by(sortsList));

    List<Question> questionList = questionHashtagRepository.findAllByHashtagTagName(hashtag)
                                                           .stream()
                                                           .map(QuestionHashtag::getQuestion)
                                                           .toList();

    return new PageImpl<>(questionList, pageable, questionList.size());
  }
}
