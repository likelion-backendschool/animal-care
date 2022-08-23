package com.codelion.animalcare.domain.hospital.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.member.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embedded;

public class LoadDoctorMyPageHospitalInfoManage {
    @AllArgsConstructor
    @Getter
    public static class ResponseDto{
        private Long id;
        private String name;

        @Embedded
        private Address address;

        private String openingHours;

        public ResponseDto(Hospital hospital) {
            this.id = hospital.getId();
            this.name = hospital.getName();
            this.address = hospital.getAddress();
            this.openingHours = hospital.getOpeningHours();
        }
    }
}
