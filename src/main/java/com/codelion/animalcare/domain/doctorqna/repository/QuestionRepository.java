package com.codelion.animalcare.domain.doctorqna.repository;

import com.codelion.animalcare.domain.doctorqna.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findAll(Pageable pageable);

    Page<Question> findByTitleContaining(String kw, Pageable pageable);

    Page<Question> findByContentContaining(String kw, Pageable pageable);

    @Query("select q from Question q where q.member.name like concat('%',UPPER(:kw),'%')")
    Page<Question> findByMemberContaining(@Param("kw")String kw, Pageable pageable);

    @Query("select q from Question q where q.title like concat('%', UPPER(:kw), '%') or q.content like concat('%', UPPER(:kw), '%')")
    Page<Question> findByTitleOrContent(@Param("kw")String kw, Pageable pageable);
    @Modifying(clearAutomatically = true)
    @Query("update Question q set q.view = q.view + 1 where q.id = :id")
    int updateView(@Param("id") Long id);

    @Modifying
    @Query("update Question q set q.likeCount = q.likeCount + 1 where q.id = :id")
    int plusLike(@Param("id") Long id);

    @Modifying
    @Query("update Question q set q.likeCount = q.likeCount - 1 where q.id = :id")
    int minusLike(@Param("id") Long id);

}

