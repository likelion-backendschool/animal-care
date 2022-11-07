package com.codelion.animalcare.domain.community.controller;

import com.codelion.animalcare.domain.community.dto.CommentDto.CommentRequestDto;
import com.codelion.animalcare.domain.community.dto.CommentDto.ModifyCommentRequestDto;
import com.codelion.animalcare.domain.community.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/*
    ToDo
    1. 댓글 작성, 수정, 삭제 사용자 인증(로그인 연동)
 */

@Controller
@RequestMapping("/usr/posts/{postId}")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping("/comments/write")
    public String write(@PathVariable Long postId, CommentRequestDto commentRequestDto, Principal principal) {
        commentService.saveComment(postId, commentRequestDto, principal);

        return "redirect:/usr/posts/%d".formatted(postId);
    }

    // 댓글 수정 화면
    @GetMapping("/comments/{commentId}/modify")
    public String modifyForm(Model model, @PathVariable Long commentId) {
        model.addAttribute("comment", commentService.findCommentById(commentId));

        return "community/commentModifyForm";
    }

    // 댓글 수정
    @PostMapping("/comments/{commentId}/modify")
    public String modify(@PathVariable Long postId, @PathVariable Long commentId, ModifyCommentRequestDto modifyCommentRequestDto) {
        commentService.modifyComment(commentId, modifyCommentRequestDto);

        return "redirect:/usr/posts/%d".formatted(postId);
    }

    // 댓글 삭제
    @GetMapping("/comments/{commentId}/delete")
    public String delete(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return "redirect:/usr/posts/%d".formatted(postId);
    }
}
