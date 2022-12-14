package com.codelion.animalcare.domain.mypage.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.codelion.animalcare.domain.user.entity.Doctor;

import java.time.LocalDateTime;
import java.sql.Date;

public class LoadDoctorMyPageInfo {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ResponseDto{
        private Long id;

        private String loginEmail;

        private String name;

        private Date birthday;

        private String major;

        private String phoneNum;

        private String introduce;

        private Integer genderId;

        private String auth;
        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
        private Hospital hospital;

        private Address address;

        public ResponseDto(Doctor doctor) {
            this.id = doctor.getId();
            this.loginEmail = doctor.getEmail();
            this.name = doctor.getName();
            this.birthday = doctor.getBirthday();
            this.major = doctor.getMajor();
            this.phoneNum = doctor.getPhoneNum();
            this.introduce = doctor.getIntroduce();
            this.genderId = doctor.getGenderId();
            this.createdAt = doctor.getCreatedAt();
            this.updatedAt = doctor.getUpdatedAt();
            this.hospital = doctor.getHospital();
            this.auth = doctor.getAuth();
            this.address =doctor.getAddress();
        }
    }
}
