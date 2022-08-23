package com.codelion.animalcare.domain.post.entity;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.member.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private int likes;

    @Column
    private int views;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

    @Builder
    private Post(Long id, LocalDateTime createdAt, String title, String content, int likes, int views, List<Comment> comments) {
        super(id, createdAt);
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.views = views;
        this.comments = comments;
    }
}
