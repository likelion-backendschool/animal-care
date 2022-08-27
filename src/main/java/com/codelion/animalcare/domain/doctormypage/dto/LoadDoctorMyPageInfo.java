package com.codelion.animalcare.domain.doctormypage.dto;


import com.codelion.animalcare.domain.user.entity.Doctor;

import java.time.LocalDateTime;

public class LoadDoctorMyPageInfo {
    public static class ResponseDto{
        private int id;

        private String loginEmail;

        private String name;

        private LocalDateTime birthday;

        private String major;

        private String phoneNum;

        private String introduce;

        private int genderId;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        public ResponseDto(Doctor doctor) {
            this.id = doctor.getGenderId();
            this.loginEmail = doctor.getEmail();
            this.name = doctor.getName();
            this.birthday = doctor.getBirthday();
            this.major = doctor.getMajor();
            this.phoneNum = doctor.getPhoneNum();
            this.introduce = doctor.getIntroduce();
            this.genderId = doctor.getGenderId();
            this.createdAt = doctor.getCreatedAt();
            this.updatedAt = doctor.getUpdatedAt();
        }
    }
}
