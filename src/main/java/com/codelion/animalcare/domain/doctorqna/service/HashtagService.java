package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.entity.Hashtag;
import com.codelion.animalcare.domain.doctorqna.repository.HashtagRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public Optional<Hashtag> findByTagName(String tagName) {

        return hashtagRepository.findByTagName(tagName);
    }

    public Hashtag save(String tagName) {

        return hashtagRepository.save(
            Hashtag.builder()
                   .tagName(tagName)
                   .build());
    }
}
