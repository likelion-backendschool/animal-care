package com.codelion.animalcare.domain.doctorqna.entity;

import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class QuestionHashtag extends BaseEntity {

    @ManyToOne
    private Question question;

    @ManyToOne
    private Hashtag hashtag;

}
