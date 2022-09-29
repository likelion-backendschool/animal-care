package com.codelion.animalcare.domain.community.service;

import com.codelion.animalcare.domain.community.entity.Comment;
import com.codelion.animalcare.domain.community.entity.Post;
import com.codelion.animalcare.domain.community.exception.CommentNotFoundException;
import com.codelion.animalcare.domain.community.exception.PostNotFoundException;
import com.codelion.animalcare.domain.community.repository.CommentRepository;
import com.codelion.animalcare.domain.community.repository.PostRepository;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.domain.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

import static com.codelion.animalcare.domain.community.dto.CommentDto.CommentRequestDto;
import static com.codelion.animalcare.domain.community.dto.CommentDto.CommentResponseDto;
import static com.codelion.animalcare.domain.community.dto.CommentDto.ModifyCommentRequestDto;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserService userService;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userService = userService;

    }

    @Transactional
    public CommentResponseDto findCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);

        if(!comment.isPresent()) {
            throw new PostNotFoundException(String.format("Post ID:%s is not found", id));
        }

        return new CommentResponseDto(comment.get());
    }

    @Transactional
    public Long saveComment(Long id, CommentRequestDto commentRequestDto, Principal principal) {
        Optional<Post> post = postRepository.findById(id);
        UserInfo user = userService.getUserInfo(principal.getName()).orElse(null);
        if(!post.isPresent()) {
            throw new PostNotFoundException(String.format("Post ID:%s is not found", id));
        }
        commentRequestDto.setPost(post.get());
        Comment comment = commentRequestDto.toEntity(user);
        commentRepository.save(comment);

        return commentRequestDto.toEntity(user).getId();
    }

    @Transactional
    public Long modifyComment(Long commentId, ModifyCommentRequestDto modifyCommentRequestDto) {
        Optional<Comment> oldComment = commentRepository.findById(commentId);

        if(!oldComment.isPresent()) {
            throw new CommentNotFoundException(String.format("Comment ID:%s is not found", commentId));
        }

        return commentRepository.save(modifyCommentRequestDto.toEntity(oldComment.get())).getId();
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
