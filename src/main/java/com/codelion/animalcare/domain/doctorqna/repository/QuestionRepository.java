package com.codelion.animalcare.domain.doctorqna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findAll(Pageable pageable);

    Page<Question> findByTitleContaining(String kw, Pageable pageable);

    Page<Question> findByContentContaining(String kw, Pageable pageable);

    @Query("select q from Question q where q.member.name like concat('%',UPPER(:kw),'%')")
    Page<Question> findByMemberContaining(@Param("kw")String kw, Pageable pageable);
    @Modifying
    @Query("update Question q set q.view = q.view + 1 where q.id = :id")
    int updateView(@Param("id") Long id);

    //test용 코드
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true)
    void truncate(); // 이거 지우면 안됨, truncateTable 하면 자동으로 이게 실행됨
}

