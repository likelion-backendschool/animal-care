package com.codelion.animalcare.domain.appointment;

public enum AppointmentStatus {

    READY, //진료 전
    COMPLETE, //진료 완료
    CANCEL, //고객이 예약진료취소
    REFUSE //의사가 예약진료취소
}
