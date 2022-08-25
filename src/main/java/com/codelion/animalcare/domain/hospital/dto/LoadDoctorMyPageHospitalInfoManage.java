package com.codelion.animalcare.domain.hospital.dto;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.global.util.OpeningHour;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class LoadDoctorMyPageHospitalInfoManage {
    @Getter
    @Setter
    public static class ResponseDto extends OpeningHour{
        private Long id;
        private String name;

        private String phoneNum;

        private String city;
        private String street;
        private String zipcode;

        private String openingHours;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private List<Doctor> doctorList;

        public ResponseDto(Hospital hospital) {
            super(hospital.getOpeningHours());
            this.id = hospital.getId();
            this.name = hospital.getName();
            this.phoneNum = hospital.getPhoneNum();
            this.city = hospital.getAddress().getCity();
            this.street = hospital.getAddress().getStreet();
            this.zipcode = hospital.getAddress().getZipcode();
            this.openingHours = hospital.getOpeningHours();
            this.createdAt = hospital.getCreatedAt();
            this.updatedAt = hospital.getUpdatedAt();
            this.doctorList = hospital.getDoctors();
        }
        public OpeningHour makeOpeningHoursToObject(){
            return new OpeningHour(openingHours);
        }

    }
}
