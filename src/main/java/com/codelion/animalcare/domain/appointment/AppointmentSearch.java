package com.codelion.animalcare.domain.appointment;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppointmentSearch {

    private String memberName; // 회원 이름
    private AppointmentStatus status; //예약 상태[COMPLETE, CANCEL]
}
