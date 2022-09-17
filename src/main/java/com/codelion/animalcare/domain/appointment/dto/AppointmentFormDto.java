package com.codelion.animalcare.domain.appointment.dto;

import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentFormDto {
    private Long doctorId;
    private Long hospitalId;
    private String date;

    private String content;
    private Long animalId;

    public LocalDateTime getDateToLocalDateTime(){
        return LocalDateTime.parse(date);
    }
}
