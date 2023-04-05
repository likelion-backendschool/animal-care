package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.entity.Hashtag;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.entity.QuestionHashtag;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionHashtagQueryService {

    private final HashtagCommandService hashtagCommandService;

    private final QuestionHashtagRepository questionHashtagRepository;
    public void saveHashtag(Question question, Set<Hashtag> hashtags) {
                hashtags.stream()
                .map(hashtag ->
                        hashtagCommandService.findByTagName(hashtag.getTagName())
                                             .orElseGet(() -> hashtagCommandService.save(hashtag.getTagName())))
                                             .forEach(hashtag -> mappedHashtagToQuestion(question, hashtag));
    }

    private Long mappedHashtagToQuestion(Question question, Hashtag hashtag) {

        return questionHashtagRepository.save(QuestionHashtag.of(question, hashtag)).getId();
    }

    public List<QuestionHashtag> findHashtagListByQuestion(Question question) {

        return questionHashtagRepository.findAllByQuestion(question);
    }
}
