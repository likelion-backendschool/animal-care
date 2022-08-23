package com.codelion.animalcare.domain.post.service;

import com.codelion.animalcare.domain.post.entity.Comment;
import com.codelion.animalcare.domain.post.entity.Post;
import com.codelion.animalcare.domain.post.exception.CommentNotFoundException;
import com.codelion.animalcare.domain.post.exception.PostNotFoundException;
import com.codelion.animalcare.domain.post.repository.CommentRepository;
import com.codelion.animalcare.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Optional;

import static com.codelion.animalcare.domain.post.dto.CommentDto.*;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
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
    public Long modifyComment(Long id, ModifyCommentRequestDto modifyCommentRequestDto) {
        Optional<Comment> oldComment = commentRepository.findById(id);
        Optional<Post> post = postRepository.findById(id);

        if(!post.isPresent()) {
            throw new PostNotFoundException(String.format("Post ID:%s is not found", id));
        }

        if(!oldComment.isPresent()) {
            throw new CommentNotFoundException(String.format("Comment ID:%s is not found", id));
        }
        modifyCommentRequestDto.setPost(post.get());

        return commentRepository.save(modifyCommentRequestDto.toEntity(oldComment.get())).getId();
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
