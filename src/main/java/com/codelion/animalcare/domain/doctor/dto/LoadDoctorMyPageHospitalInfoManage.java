package com.codelion.animalcare.domain.doctor.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;

public class LoadDoctorMyPageHospitalInfoManage {
    public static class ResponseDto{
        private String name;

        private String address;

        private String openingHours;

        public ResponseDto(Hospital hospital) {
            this.name = hospital.getName();
            this.address = hospital.getAddress();
            this.openingHours = hospital.getOpeningHours();
        }
    }
}
