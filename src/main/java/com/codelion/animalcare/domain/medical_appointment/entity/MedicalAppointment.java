package com.codelion.animalcare.domain.medical_appointment.entity;

import com.codelion.animalcare.common.entity.BaseEntity;
import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Builder
    private MedicalAppointment(Long id, LocalDateTime createdAt, String diagnosisName, String content, String status, Doctor doctor, Member member, Animal animal) {
        super(id, createdAt);
        this.diagnosisName = diagnosisName;
        this.content = content;
        this.status = status;
        this.doctor = doctor;
        this.member = member;
        this.animal = animal;
    }
}
