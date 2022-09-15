package com.codelion.animalcare.domain.appointment.entity;

import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
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
public class Appointment extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime date; // 예약날짜 및 시간


    @Column(columnDefinition = "TEXT")
    private String content;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status; // 예약상태 [COMPLETE, CANCEL] 완료, 취소


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

    @OneToOne(mappedBy = "appointment", fetch = LAZY)
    @JoinColumn( name = "diagnosis_id")
    private Diagnosis diagnosis;


    // == 연관관계 메서드 == //
    public void addMember(Member member) {
        this.member = member;
        member.getAppointments().add(this);
    }

    public void addAnimal(Animal animal) {
        this.animal = animal;
        animal.getAppointments().add(this);
    }

    public void addDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getAppointments().add(this);
    }

    public void addHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getAppointments().add(this);
    }


    //== 생성 메서드 ==//
    public static Appointment createAppointment(Member member, Animal animal, Hospital hospital, Doctor doctor, LocalDateTime appointmentDate) {
        Appointment appointment = new Appointment();
        appointment.addMember(member);

        appointment.addAnimal(animal);
        appointment.addHospital(hospital);
        appointment.addDoctor(doctor);

        appointment.setStatus(AppointmentStatus.READY);
        appointment.setDate(appointmentDate);
        return appointment;
    }

    //== 수정 메서드 ==//
    public static Appointment updateAppointment(Appointment appointment, Member member, Animal animal, Hospital hospital, Doctor doctor, LocalDateTime appointmentDate) {

        appointment.addMember(member);

        appointment.addAnimal(animal);
        appointment.addHospital(hospital);
        appointment.addDoctor(doctor);

        appointment.setStatus(AppointmentStatus.READY);
        appointment.setDate(appointmentDate);
        return appointment;
    }

    //==비즈니스 로직==//
    /**
     * 예약 취소
     */
    public void cancel() {
        this.setStatus(AppointmentStatus.CANCEL);
    }

    /**
     * 예약 상태 변경
     */
    public void updateStatus(AppointmentStatus refuse) {
        this.status = refuse;
    }

}
