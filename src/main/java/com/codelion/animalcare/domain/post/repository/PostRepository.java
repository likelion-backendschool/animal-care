package com.codelion.animalcare.domain.post.repository;

import com.codelion.animalcare.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface PostRepository extends JpaRepository<Post, Long> {
}
