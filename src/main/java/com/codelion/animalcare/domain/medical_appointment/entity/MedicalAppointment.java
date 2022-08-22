package com.codelion.animalcare.domain.medical_appointment.entity;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.medical_appointment.MedicalAppointmentStatus;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicalAppointment extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime medicalAppointmentDate; // 예약날짜 및 시간

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    // 수정: status -> medicalAppointmentStatus
    // 자바의 enum을 사용하기 위해 @Enumerated 어노테이션으로 매핑함
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MedicalAppointmentStatus medicalAppointmentStatus; // 예약상태 [COMPLETE, CANCEL] 완료, 취소


    // 수정: 지연로딩 lazy 적용
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital; // 예약 병원


    // == 연관관계 메서드 == //
    public void linkMember(Member member) {
        this.member = member;
        member.getMedicalAppointments().add(this);
    }

    public void linkAnimal(Animal animal) {
        this.animal = animal;
        animal.getMedicalAppointments().add(this);
    }

    public void linkDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getMedicalAppointments().add(this);
    }

    public void linkHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getMedicalAppointments().add(this);
    }


    @Builder
    private MedicalAppointment(Long id, LocalDateTime createdAt, String content, MedicalAppointmentStatus medicalAppointmentStatus, LocalDateTime medicalAppointmentDate, Member member, Animal animal, Hospital hospital, Doctor doctor) {
        super(id, createdAt);
        this.content = content;
        this.medicalAppointmentStatus = medicalAppointmentStatus;
        this.medicalAppointmentDate = medicalAppointmentDate;
        this.member = member;
        this.animal = animal;
        this.hospital = hospital;
        this.doctor = doctor;
    }


    //== 생성 메서드 ==//
    public static MedicalAppointment createMedicalAppointment(Member member, Animal animal, Hospital hospital, Doctor doctor) {
        MedicalAppointment medicalAppointment = new MedicalAppointment();
        medicalAppointment.linkMember(member);
        medicalAppointment.linkAnimal(animal);
        medicalAppointment.linkDoctor(doctor);
        medicalAppointment.linkHospital(hospital);

        medicalAppointment.setMedicalAppointmentStatus(MedicalAppointmentStatus.COMPLETE);
        medicalAppointment.setMedicalAppointmentDate(LocalDateTime.now());
        return medicalAppointment;
    }


    //==비즈니스 로직==//
    /**
     * 예약 취소
     */
    public void cancel() {
        this.setMedicalAppointmentStatus(MedicalAppointmentStatus.CANCEL);
    }


}
