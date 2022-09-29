package com.codelion.animalcare.domain.doctorqna.repository;

import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Answer extends BaseEntity {


    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Setter
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @Builder
    public Answer(String content, Question question, Doctor doctor) {
        this.content = content;
        this.question = question;
        this.doctor = doctor;
    }

    public void update(String content){
        this.content = content;
    }

    /*
    추후에 like entity 따로 만들어서 구성
    @Column
    private int like;

    Forienkey(doctor_id) 추후에 구성
    */

}
