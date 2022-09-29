package com.codelion.animalcare.domain.community.dto;

import com.codelion.animalcare.domain.community.dto.CommentDto.CommentResponseDto;
import com.codelion.animalcare.domain.community.entity.Post;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {
    @Getter
    @Setter
    public static class PostRequestDto {
        private String title;
        private String content;

        public Post toEntity(UserInfo member) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .likes(0)
                    .views(0)
                    .member(member)
                    .build();
        }
    }

    @Getter
    public static class PostResponseDto {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String title;
        private String content;
        private int likes;
        private int views;

        private UserInfo member;
        private List<CommentResponseDto> comments;

        public PostResponseDto(Post post){
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.likes = post.getLikes();
            this.views = post.getViews();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
            this.member = post.getMember();
            this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    public static class ModifyPostRequestDto {
        private String title;
        private String content;

        public Post toEntity(Post oldPost) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }

}
