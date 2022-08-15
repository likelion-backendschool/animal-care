package com.codelion.animalcare.domain.medical_record.entity

import com.codelion.animalcare.common.entity.BaseEntity
import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor

import javax.persistence.Column
import javax.persistence.Entity

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MedicalRecord extends BaseEntity{

    @Column(nullable = false, length = 30)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(nullable = false, length = 20)
    private String cost;

    @Column(nullable = false, length = 45)
    private String diagnosisName;
}
