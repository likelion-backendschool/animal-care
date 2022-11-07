package com.codelion.animalcare.domain.mypage.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
/*import com.codelion.animalcare.domain.member.Address;*/

public class LoadDoctorMyPageHospitalInfoManage {
    public static class ResponseDto{
        private String name;

        /*@Embedded
        private Address address;*/

        private String openingHours;

        public ResponseDto(Hospital hospital) {
            this.name = hospital.getName();
            /*this.address = hospital.getAddress();*/
            this.openingHours = hospital.getOpeningHours();
        }
    }
}
