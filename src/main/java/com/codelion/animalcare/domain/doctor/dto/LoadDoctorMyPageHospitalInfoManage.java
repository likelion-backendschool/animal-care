package com.codelion.animalcare.domain.doctor.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.member.Address;
import lombok.Getter;

import javax.persistence.Embedded;

public class LoadDoctorMyPageHospitalInfoManage {
    @Getter
    public static class ResponseDto{
        private String name;

        @Embedded
        private Address address;

        private String openingHours;

        public ResponseDto(Hospital hospital) {
            this.name = hospital.getName();
            this.address = hospital.getAddress();
            this.openingHours = hospital.getOpeningHours();
        }
    }
}