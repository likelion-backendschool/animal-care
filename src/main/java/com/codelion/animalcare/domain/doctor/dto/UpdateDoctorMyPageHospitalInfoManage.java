package com.codelion.animalcare.domain.doctor.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.member.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.time.LocalDateTime;

public class UpdateDoctorMyPageHospitalInfoManage {
    @Getter
    @Setter
    public static class RequestDto{
        private String name;

        @Embedded
        private Address address;

        private String openingHours;

        public Hospital toEntity(Hospital hospital){
            return Hospital.builder()
                    .id(hospital.getId())
                    .name(name)
                    .address(address)
                    .openingHours(openingHours)
                    .createdAt(hospital.getCreatedAt())
                    .build();
        }
    }
}
