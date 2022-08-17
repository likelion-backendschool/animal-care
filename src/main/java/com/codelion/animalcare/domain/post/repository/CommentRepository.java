package com.codelion.animalcare.domain.post.repository;

import com.codelion.animalcare.domain.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
