package com.codelion.animalcare.domain.doctorqna.repository;

import com.codelion.animalcare.domain.doctorqna.entity.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long> {

    boolean existsByQuestion_IdAndMember_Id(Long questionId, Long memberId);

    void deleteByQuestion_IdAndMember_Id(Long questionId, Long memberId);
}
