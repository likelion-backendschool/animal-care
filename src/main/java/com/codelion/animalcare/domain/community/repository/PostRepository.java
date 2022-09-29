package com.codelion.animalcare.domain.community.repository;

import com.codelion.animalcare.domain.community.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContaining(String kw, Pageable pageable);

    Page<Post> findByContentContaining(String kw, Pageable pageable);

    @Query("select p from Post p where p.member.name like concat('%',UPPER(:kw),'%')")
    Page<Post> findByMemberContaining(@Param("kw")String kw, Pageable pageable);

    @Modifying
    @Query("update Post p set p.views = p.views + 1 where p.id = :id")
    int updateViews(@Param("id") Long id);

    @Modifying
    @Query("update Post p set p.likes = p.likes + 1 where p.id = :id")
    int updateLikes(@Param("id") Long id);
}
