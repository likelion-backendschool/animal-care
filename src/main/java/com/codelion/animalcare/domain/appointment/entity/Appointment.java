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


    /**
     * 예약날짜(시간) 변경
     */
    public void updateAppointmentDate(LocalDateTime date) {
        this.date = date;
    }


    /**
     * 진료 상태 변경: 수의사가 취소
     */
    public void updateStatusToRefuse(AppointmentStatus refuse) {
        this.status = refuse;
    }


    /**
     * 진료 상태 변경: 멤버가 취소
     */
    public void updateStatusToCancel(AppointmentStatus cancel) {
        this.status = cancel;
    }


    /**
     * 진료 상태 변경: 수의사가 진단서 작성시 진료상태 완료
     */
    public void updateStatusToComplete(AppointmentStatus complete) {
        this.status = complete;
    }


}
