package com.codelion.animalcare.domain.appointment.dto;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class LoadMyPageDoctorAppointment {
    @Getter
    @Setter
    @AllArgsConstructor
    static public class ResponseDto{
        private Long id;
        private LocalDateTime appointmentDate; // 예약날짜 및 시간

        private String content;

        private AppointmentStatus appointmentStatus; // 예약상태 [COMPLETE, CANCEL, REFUSE] 완료, 취소

        private Doctor doctor;

        private Member member;

        private Animal animal;

        private Hospital hospital; // 예약 병원

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public ResponseDto(Appointment appointment){
            this.id = appointment.getId();
            this.appointmentDate = appointment.getDate();
            this.content = appointment.getContent();
            this.appointmentStatus = appointment.getStatus();
            this.doctor = appointment.getDoctor();
            this.member = appointment.getMember();
            this.animal = appointment.getAnimal();
            this.hospital = appointment.getHospital();
            this.createdAt = appointment.getCreatedAt();
            this.updatedAt = appointment.getUpdatedAt();
        }
    }
}
