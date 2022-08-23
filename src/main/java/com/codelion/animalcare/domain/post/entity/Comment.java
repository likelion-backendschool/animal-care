package com.codelion.animalcare.domain.post.entity;

import com.codelion.animalcare.domain.member.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Column(nullable = false)
    private String content;

    @Column
    private int likes;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Comment(Long id, LocalDateTime createdAt, String content, int likes, Post post) {
        super(id, createdAt);
        this.content = content;
        this.likes = likes;
        this.post = post;
    }
}
