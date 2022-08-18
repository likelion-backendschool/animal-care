package com.codelion.animalcare.domain.doctorQna.repository;

import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Question extends BaseEntity {

    @Column(length = 40, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "Integer default 0", nullable = false)
    private int view;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList = new ArrayList<>();

    @Builder
    public Question(String title, String content, int view, List<Answer> answerList) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.answerList = answerList;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        getAnswerList().add(answer);
    }


    /*
    추후에 like entity 따로 만들어서 구성
    @Column
    private int like;

    Forienkey(animal_breed_id), (patient_id) 추후에 구성
    */
}
