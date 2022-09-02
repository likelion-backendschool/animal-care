package com.codelion.animalcare.domain.post.dto;

import com.codelion.animalcare.domain.post.entity.Comment;
import com.codelion.animalcare.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {
    @Getter
    @Setter
    public static class CommentRequestDto {
        private String content;
        private Post post;

        public Comment toEntity() {
            return Comment.builder()
                    .content(content)
                    .likes(0)
                    .post(post)
                    .build();
        }
    }

    @Getter
    public static class CommentResponseDto {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String content;
        private int likes;
        private Long postId;

        public CommentResponseDto(Comment comment) {
            this.id = comment.getId();
            this.createdAt = comment.getCreatedAt();
            this.updatedAt = comment.getUpdatedAt();
            this.content = comment.getContent();
            this.likes = comment.getLikes();
            this.postId = comment.getPost().getId();
        }
    }

    @Getter
    @Setter
    public static class ModifyCommentRequestDto {
        private String content;
        private Post post;

        public Comment toEntity(Comment oldComment) {
            return Comment.builder()
                    .id(oldComment.getId())
                    .createdAt(oldComment.getCreatedAt())
                    .content(content)
                    .likes(oldComment.getLikes())
                    .post(oldComment.getPost())
                    .build();
        }
    }
}
