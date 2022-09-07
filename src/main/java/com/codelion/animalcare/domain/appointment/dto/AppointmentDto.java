package com.codelion.animalcare.domain.appointment.dto;

import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class AppointmentDto {

        private Long appointmentId;
        private String memberName;
        private String animalName;
        private String hospitalName;
        private String doctorName;

        private LocalDateTime date;
        private AppointmentStatus status;


        public AppointmentDto(Appointment appointment) {
            appointmentId = appointment.getId();
            memberName = appointment.getMember().getName();
            animalName = appointment.getAnimal().getName();
            hospitalName = appointment.getHospital().getName();
            doctorName = appointment.getDoctor().getName();
            date = appointment.getDate();
            status = appointment.getStatus();
        }

//    public void update(String memberName, String animalName, String hospitalName, String doctorName, LocalDateTime date) {
//        this.memberName = memberName;
//        this.animalName = animalName;
//        this.hospitalName = hospitalName;
//        this.doctorName = doctorName;
//        this.date = date;
//    }
}