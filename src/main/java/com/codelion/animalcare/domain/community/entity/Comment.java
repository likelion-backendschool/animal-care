package com.codelion.animalcare.domain.community.entity;


import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Comment extends BaseEntity {
    @Column(nullable = false)
    private String content;

    @Column
    private int likes;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

//    @Builder
//    private Comment(Long id, LocalDateTime createdDate, String content, int likes, Post post) {
//        super(id, createdDate);
//        this.content = content;
//        this.likes = likes;
//        this.post = post;
//    }
}
