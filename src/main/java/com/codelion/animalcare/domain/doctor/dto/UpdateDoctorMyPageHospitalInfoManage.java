package com.codelion.animalcare.domain.doctor.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class UpdateDoctorMyPageHospitalInfoManage {
    @Getter
    @Setter
    public static class RequestDto{
        private String name;

        private String address;

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
