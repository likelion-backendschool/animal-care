package com.codelion.animalcare.domain.post.controller;

import com.codelion.animalcare.domain.post.dto.PostDto.ModifyPostRequestDto;
import com.codelion.animalcare.domain.post.dto.PostDto.PostRequestDto;
import com.codelion.animalcare.domain.post.dto.PostDto.PostResponseDto;
import com.codelion.animalcare.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usr/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @GetMapping("/")
//    public String index(Model model, @PageableDefault(sort ="created_date", direction = Sort.Direction.DESC) Pageable pageable) {
//        model.addAttribute("posts", postService.listPost(pageable));
//
//        return "index";
//    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
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

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity create(@RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.savePost(postRequestDto));
    }

}
