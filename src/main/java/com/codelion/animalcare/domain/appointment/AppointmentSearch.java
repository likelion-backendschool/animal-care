package com.codelion.animalcare.domain.appointment;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AppointmentSearch {

    private String memberName; // 회원 이름
    private AppointmentStatus status; //예약 상태[COMPLETE, CANCEL]
    private LocalDateTime date; //예약 날짜
}
