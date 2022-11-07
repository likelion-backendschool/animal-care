package com.codelion.animalcare.domain.community.repository;

import com.codelion.animalcare.domain.community.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
