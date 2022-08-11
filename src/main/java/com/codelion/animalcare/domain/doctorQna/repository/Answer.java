package com.codelion.animalcare.domain.doctorQna.repository;

import com.codelion.animalcare.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long QuestionId;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Question question;

    @Builder
    public Answer(String title, String content, Question question) {
        this.title = title;
        this.content = content;
        this.question = question;
    }

    /*
    추후에 like entity 따로 만들어서 구성
    @Column
    private int like;

    Forienkey(doctor_id) 추후에 구성
    */

}
