package com.codelion.animalcare.domain.community.service;

import com.codelion.animalcare.domain.community.entity.Comment;
import com.codelion.animalcare.domain.community.entity.Post;
import com.codelion.animalcare.domain.community.exception.CommentNotFoundException;
import com.codelion.animalcare.domain.community.exception.PostNotFoundException;
import com.codelion.animalcare.domain.community.repository.CommentRepository;
import com.codelion.animalcare.domain.community.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Optional;

import static com.codelion.animalcare.domain.community.dto.CommentDto.*;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
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
    public Long saveComment(Long id, CommentRequestDto commentRequestDto) {
        Optional<Post> post = postRepository.findById(id);

        if(!post.isPresent()) {
            throw new PostNotFoundException(String.format("Post ID:%s is not found", id));
        }
        commentRequestDto.setPost(post.get());
        Comment comment = commentRequestDto.toEntity();
        commentRepository.save(comment);

        return commentRequestDto.toEntity().getId();
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
