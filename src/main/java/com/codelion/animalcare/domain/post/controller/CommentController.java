package com.codelion.animalcare.domain.post.controller;

import com.codelion.animalcare.domain.post.dto.CommentDto.CommentRequestDto;
import com.codelion.animalcare.domain.post.dto.CommentDto.CommentResponseDto;
import com.codelion.animalcare.domain.post.dto.CommentDto.ModifyCommentRequestDto;
import com.codelion.animalcare.domain.post.dto.PostDto.PostResponseDto;
import com.codelion.animalcare.domain.post.service.CommentService;
import com.codelion.animalcare.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usr/posts/{post_id}")
public class CommentController {
    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    // 게시글의 댓글 조회
    @GetMapping("/comments")
    public List<CommentResponseDto> listComments(@PathVariable Long post_id) {
        PostResponseDto postResponseDto = postService.findPostById(post_id);
        return postResponseDto.getComments();
    }

    // 댓글 작성
    @PostMapping("/comments/write")
    public ResponseEntity create(@PathVariable Long post_id, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.saveComment(post_id, commentRequestDto));
    }

    // 댓글 수정
    @PostMapping("/comments/{comment_id}/modify")
    public ResponseEntity modify(@PathVariable Long comment_id, @RequestBody ModifyCommentRequestDto modifyCommentRequestDto) {
        return ResponseEntity.ok(commentService.modifyComment(comment_id, modifyCommentRequestDto));
    }

    // 댓글 삭제
    @GetMapping("/comments/{comment_id}/delete")
    public void delete(@PathVariable Long comment_id) {
        commentService.deleteComment(comment_id);
    }
}
