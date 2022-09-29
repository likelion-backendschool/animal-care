package com.codelion.animalcare.domain.appointment.entity;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter
@Setter
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

    public static Appointment updateAppointmentDate(Appointment appointment, LocalDateTime appointmentDate) {
        appointment.setDate(appointmentDate);
        return appointment;
    }

//    public void Appointment updateAppointmentDate(LocalDateTime date) {
//        this.date = date;
//    }


    /**
     * 예약 취소
     */
    public void cancelStatus(AppointmentStatus cancel) {
        this.status = cancel;
    }


    /**
     * 예약 상태 변경
     */
    public void updateStatus(AppointmentStatus refuse) {
        this.status = refuse;
    }


}
