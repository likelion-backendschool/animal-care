package com.codelion.animalcare.domain.doctorqna.repository;

import com.codelion.animalcare.domain.doctorqna.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByTagName(String tagName);
}
