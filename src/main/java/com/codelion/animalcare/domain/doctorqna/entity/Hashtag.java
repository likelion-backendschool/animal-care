package com.codelion.animalcare.domain.doctorqna.entity;

import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor
@Entity
public class Hashtag extends BaseEntity {

    private String tagName;

    @Builder
    public Hashtag(String tagName) {
        this.tagName = tagName;
    }
}
