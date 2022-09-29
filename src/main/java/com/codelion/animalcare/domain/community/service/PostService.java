package com.codelion.animalcare.domain.community.service;

import com.codelion.animalcare.domain.community.dto.PostDto.ModifyPostRequestDto;
import com.codelion.animalcare.domain.community.dto.PostDto.PostRequestDto;
import com.codelion.animalcare.domain.community.dto.PostDto.PostResponseDto;
import com.codelion.animalcare.domain.community.entity.Post;
import com.codelion.animalcare.domain.community.exception.PostNotFoundException;
import com.codelion.animalcare.domain.community.repository.PostRepository;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional
    public Page<Post> listPost(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional
    public Page<Post> findAll(int page, String type, String kw) {
        List<Sort.Order> sortsList = new ArrayList<>();
        sortsList.add(Sort.Order.desc("createdAt"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sortsList));

        switch (type != null ? type : " ") {
            case "title" -> {
                return postRepository.findByTitleContaining(kw, pageable);
            }
            case "content" -> {
                return postRepository.findByContentContaining(kw, pageable);
            }
            case "member" -> {
                return postRepository.findByMemberContaining(kw, pageable);
            }
            default -> {
                return postRepository.findAll(pageable);
            }
        }

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
    public Long savePost(PostRequestDto postRequestDto, Principal principal) {
        UserInfo user = userService.getUserInfo(principal.getName()).orElse(null);

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
