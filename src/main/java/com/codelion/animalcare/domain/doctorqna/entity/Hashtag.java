package com.codelion.animalcare.domain.doctorqna.entity;

import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;

@Getter
@Entity
public class Hashtag extends BaseEntity {

    private String tag;
}
