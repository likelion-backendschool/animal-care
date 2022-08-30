package com.codelion.animalcare.domain.medical_appointment.entity;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.medical_appointment.MedicalAppointmentStatus;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.codelion.animalcare.domain.animal.entity.Animal;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class MedicalAppointment extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime date; // 예약날짜 및 시간


    @Column(columnDefinition = "TEXT")
    private String content;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MedicalAppointmentStatus status; // 예약상태 [COMPLETE, CANCEL] 완료, 취소


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


     // == 연관관계 메서드 == //
    public void addMember(Member member) {
        this.member = member;
        member.getMedicalAppointments().add(this);
    }

    public void addAnimal(Animal animal) {
        this.animal = animal;
        animal.getMedicalAppointments().add(this);
    }

    public void addDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getMedicalAppointments().add(this);
    }

    public void addHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getMedicalAppointments().add(this);
    }



//    @Builder
//    private MedicalAppointment(Long id, LocalDateTime createdAt, String content, MedicalAppointmentStatus medicalAppointmentStatus, LocalDateTime medicalAppointmentDate, Doctor doctor, Member member, Animal animal, Hospital hospital) {
//        super(id, createdAt);
//        this.content = content;
//        this.medicalAppointmentStatus = medicalAppointmentStatus;
//        this.medicalAppointmentDate = medicalAppointmentDate;
//        this.doctor = doctor;
//        this.member = member;
//        this.animal = animal;
//        this.hospital = hospital;
//    }


    //== 생성 메서드 ==//
    public static MedicalAppointment createMedicalAppointment(Member member,Animal animal, Hospital hospital, Doctor doctor, LocalDateTime medicalAppointmentDate) {
        MedicalAppointment medicalAppointment = new MedicalAppointment();
        medicalAppointment.addMember(member);

        medicalAppointment.addAnimal(animal);
        medicalAppointment.addHospital(hospital);
        medicalAppointment.addDoctor(doctor);

        medicalAppointment.setStatus(MedicalAppointmentStatus.COMPLETE);
        medicalAppointment.setDate(medicalAppointmentDate);
        return medicalAppointment;
    }


    //==비즈니스 로직==//
    /**
     * 예약 취소
     */
    public void cancel() {
        this.setStatus(MedicalAppointmentStatus.CANCEL);
    }


}
