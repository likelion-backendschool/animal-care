package com.codelion.animalcare.domain.appointment.dto;

import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentModifyDto {

    private Long appointmentId;
    private String memberName;
    private String animalName;
    private String hospitalName;
    private String doctorName;

    private LocalDateTime date;
    private AppointmentStatus status;


    public AppointmentModifyDto(Appointment appointment) {
        appointmentId = appointment.getId();

        memberName = appointment.getMember().getName();
        animalName = appointment.getAnimal().getName();
        hospitalName = appointment.getHospital().getName();
        doctorName = appointment.getDoctor().getName();
        date = appointment.getDate();
        status = appointment.getStatus();
    }
}
