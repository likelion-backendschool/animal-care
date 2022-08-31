package com.codelion.animalcare.domain.post.service;

import com.codelion.animalcare.domain.post.dto.PostDto.ModifyPostRequestDto;
import com.codelion.animalcare.domain.post.dto.PostDto.PostRequestDto;
import com.codelion.animalcare.domain.post.dto.PostDto.PostResponseDto;
import com.codelion.animalcare.domain.post.entity.Post;
import com.codelion.animalcare.domain.post.exception.PostNotFoundException;
import com.codelion.animalcare.domain.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Page<Post> listPost(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional
    public PostResponseDto findPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);

        if(!post.isPresent()) {
            throw new PostNotFoundException(String.format("Post ID:%s is not found", id));
        }

        return new PostResponseDto(post.get());
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Long modifyPost(Long id, ModifyPostRequestDto modifyPostRequestDto) {
        Optional<Post> oldPost = postRepository.findById(id);

        if(!oldPost.isPresent()) {
            throw new PostNotFoundException(String.format("Post ID:%s is not found", id));
        }
        return postRepository.save(modifyPostRequestDto.toEntity(oldPost.get())).getId();
    }

    @Transactional
    public Long savePost(PostRequestDto postRequestDto) {
        return postRepository.save(postRequestDto.toEntity()).getId();
    }

    @Transactional
    public int updatePostViews(Long id) {
        return postRepository.updateViews(id);
    }

    @Transactional
    public int updatePostLikes(Long id) {
        return postRepository.updateLikes(id);
    }
}
