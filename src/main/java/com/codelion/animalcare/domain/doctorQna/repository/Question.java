package com.codelion.animalcare.domain.doctorQna.repository;

import com.codelion.animalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "Integer default 0", nullable = false)
    private int view;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @Builder
    public Question(String title, String content, int view, List<Answer> answerList) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.answerList = answerList;
    }
    /*
    추후에 like entity 따로 만들어서 구성
    @Column
    private int like;

    Forienkey(animal_breed_id), (patient_id) 추후에 구성
    */
}
