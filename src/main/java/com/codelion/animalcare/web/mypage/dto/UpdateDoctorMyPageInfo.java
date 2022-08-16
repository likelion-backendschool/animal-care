package com.codelion.animalcare.web.mypage.dto;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import lombok.Getter;

import java.sql.Date;

public class UpdateDoctorMyPageInfo {
    @Getter
    public static class RequestDto{
        private String loginEmail;

        private String name;

        private Date birthday;

        private String major;

        private String phoneNum;

        private String introduce;

        private int genderId;

        public Doctor toEntity(Doctor doctor){
            return Doctor.builder()
                    .id(doctor.getId())
                    .loginEmail(loginEmail)
                    .loginPwd(doctor.getLoginPwd())
                    .name(name)
                    .birthday(birthday)
                    .major(major)
                    .phoneNum(phoneNum)
                    .introduce(introduce)
                    .genderId(genderId)
                    .createdAt(doctor.getCreatedAt())
                    .build();
        }
    }
}
