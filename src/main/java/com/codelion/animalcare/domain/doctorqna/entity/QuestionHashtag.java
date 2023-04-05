package com.codelion.animalcare.domain.doctorqna.entity;

import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class QuestionHashtag extends BaseEntity {

    @ManyToOne
    private Question question;

    @ManyToOne
    private Hashtag hashtag;

    public QuestionHashtag(Question question, Hashtag hashtag) {
        this.question = question;
        this.hashtag = hashtag;
    }

    public static QuestionHashtag of(Question question, Hashtag hashtag) {
        return new QuestionHashtag(question, hashtag);
    }


}
