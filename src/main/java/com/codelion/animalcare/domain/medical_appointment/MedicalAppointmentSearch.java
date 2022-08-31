package com.codelion.animalcare.domain.medical_appointment;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MedicalAppointmentSearch {

    private String memberName; // 회원 이름
    private MedicalAppointmentStatus status; //예약 상태[COMPLETE, CANCEL]
}
