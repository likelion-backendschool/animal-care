package com.codelion.animalcare.domain.doctorqna.entity;

import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Question extends BaseEntity {

    @Column(length = 200, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "Integer default 0", nullable = false)
    private int view;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList = new ArrayList<>();

    @Column(columnDefinition = "Integer default 0", nullable = false)
    private int likeCount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.codelion.animalcare.domain.doctorqna.entity.QuestionLike> QuestionLike;
    @Builder
    public Question(String title, String content, int view, int likeCount, Member member) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.member = member;
        this.likeCount = likeCount;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        //getAnswerList().add(answer);

    }


}
