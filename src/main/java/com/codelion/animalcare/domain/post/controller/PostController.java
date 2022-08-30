package com.codelion.animalcare.domain.post.controller;

import com.codelion.animalcare.domain.post.dto.PostDto.ModifyPostRequestDto;
import com.codelion.animalcare.domain.post.dto.PostDto.PostRequestDto;
import com.codelion.animalcare.domain.post.dto.PostDto.PostResponseDto;
import com.codelion.animalcare.domain.post.entity.Post;
import com.codelion.animalcare.domain.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usr/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 커뮤니티 게시글 페이징
    @GetMapping("/list")
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
    @GetMapping("/")
    public PostResponseDto detail(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    // 게시글 삭제
    @GetMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        postService.deletePost(id);
    }

    // 게시글 수정
    @PostMapping("/{id}/modify")
    public ResponseEntity modify(@PathVariable Long id, @RequestBody ModifyPostRequestDto modifyPostRequestDto) {
        return ResponseEntity.ok(postService.modifyPost(id, modifyPostRequestDto));
    }

    // 게시글 작성 화면
    @GetMapping("/write")
    public String writeForm(PostRequestDto postRequestDto) {
        return "community/communityForm";
    }


    @PostMapping("/write")
    public String write(PostRequestDto postRequestDto) {
        postService.savePost(postRequestDto);
        return "redirect:/usr/posts/list";
    }

}
