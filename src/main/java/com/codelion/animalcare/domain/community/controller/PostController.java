package com.codelion.animalcare.domain.community.controller;

import com.codelion.animalcare.domain.community.dto.CommentDto.CommentRequestDto;
import com.codelion.animalcare.domain.community.dto.PostDto.ModifyPostRequestDto;
import com.codelion.animalcare.domain.community.dto.PostDto.PostRequestDto;
import com.codelion.animalcare.domain.community.entity.Post;
import com.codelion.animalcare.domain.community.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  /*
        ToDo
        1. 게시글 작성, 수정, 삭제 사용자 인증(로그인 연동)
     */

@Controller
@RequestMapping("/usr/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 목록 페이징
    @GetMapping("")
    public String list(Model model, @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> postList = postService.listPost(pageable);

        model.addAttribute("postList", postList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", postList.hasNext());
        model.addAttribute("hasPrev", postList.hasPrevious());

        return "community/communityList";
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable Long id, HttpServletRequest request, HttpServletResponse response, CommentRequestDto commentRequestDto) {
        // 조회수 중복 방지
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(var cookie : cookies) {
                if(cookie.getName().equals("CommunityView")) {
                    oldCookie = cookie;
                }
            }
        }

        if(oldCookie != null) {
            if(!oldCookie.getValue().contains("[" + id.toString() + "]")) {
                postService.updatePostViews(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            postService.updatePostViews(id);
            Cookie newCookie = new Cookie("CommunityView", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
        model.addAttribute("post", postService.findPostById(id));

        return "community/communityDetail";
    }

    // 게시글 삭제
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.deletePost(id);

        return "redirect:/usr/posts";
    }

    // 게시글 수정 화면
    @GetMapping("/{id}/modify")
    public String modifyForm(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.findPostById(id));

        return "community/communityModifyForm";
    }

    // 게시글 수정
    @PostMapping("/{id}/modify")
    public String modify(@PathVariable Long id, ModifyPostRequestDto modifyPostRequestDto) {
        postService.modifyPost(id, modifyPostRequestDto);

        return "redirect:/usr/posts/%d".formatted(id);
    }

    // 게시글 작성 화면
    @GetMapping("/write")
    public String writeForm(PostRequestDto postRequestDto) {
        return "community/communityForm";
    }

    // 게시글 작성
    @PostMapping("/write")
    public String write(PostRequestDto postRequestDto) {
        postService.savePost(postRequestDto);

        return "redirect:/usr/posts";
    }

    // 게시글 추천
    @GetMapping("/{id}/like")
    public String like(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        // 추천수 중복 방지
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(var cookie : cookies) {
                if(cookie.getName().equals("CommunityLike")) {
                    oldCookie = cookie;
                }
            }
        }

        if(oldCookie != null) {
            if(!oldCookie.getValue().contains("[" + id.toString() + "]")) {
                postService.updatePostLikes(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            postService.updatePostLikes(id);
            Cookie newCookie = new Cookie("CommunityLike", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }

        return "redirect:/usr/posts/%d".formatted(id);
    }
}
