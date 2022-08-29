package com.codelion.animalcare.domain.medical_appointment.dto;

import com.codelion.animalcare.domain.medical_appointment.MedicalAppointmentStatus;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MedicalAppointmentDto {

        private Long medicalAppointmentId;
        private String memberName;
        private String animalName;
        private String hospitalName;
        private String doctorName;

        private LocalDateTime date;
        private MedicalAppointmentStatus status;


        public MedicalAppointmentDto(MedicalAppointment medicalAppointment) {
            medicalAppointmentId = medicalAppointment.getId();
            memberName = medicalAppointment.getMember().getName();
            animalName = medicalAppointment.getAnimal().getName();
            hospitalName = medicalAppointment.getHospital().getName();
            doctorName = medicalAppointment.getDoctor().getName();
            date = medicalAppointment.getDate();
            status = medicalAppointment.getStatus();
        }
    }