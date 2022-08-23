package com.codelion.animalcare.domain.doctor.dto;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

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

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
        private Hospital hospital;

        public ResponseDto(Doctor doctor) {
            this.id = doctor.getId();
            this.loginEmail = doctor.getLoginEmail();
            this.name = doctor.getName();
            this.birthday = doctor.getBirthday();
            this.major = doctor.getMajor();
            this.phoneNum = doctor.getPhoneNum();
            this.introduce = doctor.getIntroduce();
            this.genderId = doctor.getGenderId();
            this.createdAt = doctor.getCreatedAt();
            this.updatedAt = doctor.getUpdatedAt();
            this.hospital = doctor.getHospital();
        }
    }
}
