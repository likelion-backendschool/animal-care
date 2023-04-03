package com.codelion.animalcare.domain.doctorqna.entity;

import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class QuestionHashtag extends BaseEntity {

    @ManyToOne
    private Question question;

    @ManyToOne
    private Hashtag hashtag;
}
