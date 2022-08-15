package com.codelion.animalcare.domain.medical_appointment.entity;

import com.codelion.animalcare.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicalAppointment extends BaseEntity {

    @Column(nullable = false, length = 45)
    private String diagnosisName;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(nullable = false, length = 45)
    private String status;

    @Builder
    public MedicalAppointment(Long id, LocalDateTime createdAt, String diagnosisName, String content, String status) {
        super(id, createdAt);
        this.diagnosisName = diagnosisName;
        this.content = content;
        this.status = status;
    }
}
