package com.codelion.animalcare.domain.medicalAppointment;


import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MedicalAppointmentSearch {

    private String memberName; // 회원 이름
    private MedicalAppointmentStatus medicalAppointmentStatus; //예약 상태[COMPLETE, CANCEL]
}
