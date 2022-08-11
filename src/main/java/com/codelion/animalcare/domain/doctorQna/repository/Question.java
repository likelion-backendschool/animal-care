package com.codelion.animalcare.domain.doctorQna.repository;

import com.codelion.animalcare.domain.BaseTimeEntity;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@NoArgsConstructor
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long QuestionId;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "Integer default 0", nullable = false)
    private int view;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // 이 클래스가 One, Many는 Answer
    private List<Answer> answerList;
    /*
    추후에 like entity 따로 만들어서 구성
    @Column
    private int like;

    Forienkey(animal_breed_id), (patient_id) 추후에 구성
    */
}
