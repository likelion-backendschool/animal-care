package com.codelion.animalcare.domain.medical_appointment.dto;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.medical_appointment.MedicalAppointmentStatus;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

public class LoadMyPageDoctorMedicalAppointment {
    @Getter
    @Setter
    @AllArgsConstructor
    static public class ResponseDto{
        private Long id;
        private LocalDateTime medicalAppointmentDate; // 예약날짜 및 시간

        private String content;

        private MedicalAppointmentStatus medicalAppointmentStatus; // 예약상태 [COMPLETE, CANCEL, REFUSE] 완료, 취소

        private Doctor doctor;

        private Member member;

        private Animal animal;

        private Hospital hospital; // 예약 병원

        private Diagnosis diagnosis;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public ResponseDto(MedicalAppointment medicalAppointment){
            this.id = medicalAppointment.getId();
            this.medicalAppointmentDate = medicalAppointment.getMedicalAppointmentDate();
            this.content = medicalAppointment.getContent();
            this.medicalAppointmentStatus = medicalAppointment.getMedicalAppointmentStatus();
            this.doctor = medicalAppointment.getDoctor();
            this.member = medicalAppointment.getMember();
            this.animal = medicalAppointment.getAnimal();
            this.hospital = medicalAppointment.getHospital();
            this.diagnosis = medicalAppointment.getDiagnosis();
            this.createdAt = medicalAppointment.getCreatedAt();
            this.updatedAt = medicalAppointment.getUpdatedAt();
        }
    }
}
