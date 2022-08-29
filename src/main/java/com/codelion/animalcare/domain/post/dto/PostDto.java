package com.codelion.animalcare.domain.post.dto;

import com.codelion.animalcare.domain.post.dto.CommentDto.CommentResponseDto;
import com.codelion.animalcare.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {
    @Getter
    public static class PostRequestDto {
        private String title;
        private String content;

        public Post toEntity() {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .likes(0)
                    .views(0)
                    .build();
        }
    }

    @Getter
    public static class PostResponseDto {
        private Long id;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private String title;
        private String content;
        private int likes;
        private int views;
        private List<CommentResponseDto> comments;

        public PostResponseDto(Post post){
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.likes = post.getLikes();
            this.views = post.getViews();
            this.createdDate = post.getCreatedAt();
            this.updatedDate = post.getUpdatedAt();
            this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        }
    }

    @Getter
    public static class ModifyPostRequestDto {
        private String title;
        private String content;

        public Post toEntity(Post oldPost) {
            return Post.builder()
                    .id(oldPost.getId())
                    .createdAt(oldPost.getCreatedAt())
                    .title(title)
                    .content(content)
                    .likes(oldPost.getLikes())
                    .views(oldPost.getViews())
                    .build();
        }
    }

}
