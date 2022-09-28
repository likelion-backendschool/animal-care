package com.codelion.animalcare.domain.appointment.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentFormDto {
    private Long doctorId;
    private Long hospitalId;
    private String date;


    private String content;

    @NotNull(message = "애완동물을 고르지 않았습니다.")
    private Long animalId;

    public LocalDateTime getDateToLocalDateTime(){
        return LocalDateTime.parse(date);
    }
}
