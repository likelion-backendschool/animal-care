package com.codelion.animalcare.domain.hospital.dto;

import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
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
        private String detail;

        private String openingHours;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Double latitude; // 위도
        private Double longitude; // 경도
        private List<Doctor> doctorList;

        public ResponseDto(Hospital hospital) {
            super(hospital.getOpeningHours());

            Address address = hospital.getAddress();
            this.id = hospital.getId();
            this.name = hospital.getName();
            this.phoneNum = hospital.getPhoneNum();
            if(address != null){
                this.city = address.getCity();
                this.street = address.getStreet();
                this.zipcode = address.getZipcode();
                this.detail = address.getDetail();
                this.latitude = address.getLatitude();
                this.longitude = address.getLongitude();
            }
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
