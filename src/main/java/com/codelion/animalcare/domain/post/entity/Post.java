package com.codelion.animalcare.domain.post.entity;

import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class Post extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private int likes;

    @Column
    private int views;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

//    @Builder
//    private Post(Long id, LocalDateTime createdDate, String title, String content, int likes, int views, List<Comment> comments) {
//        super(id, createdDate);
//        this.title = title;
//        this.content = content;
//        this.likes = likes;
//        this.views = views;
//        this.comments = comments;
//    }
}
