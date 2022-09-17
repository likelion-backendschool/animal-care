package com.codelion.animalcare.domain.doctorqna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long> {

    boolean existsByQuestion_IdAndMember_Id(Long questionId, Long memberId);
}
