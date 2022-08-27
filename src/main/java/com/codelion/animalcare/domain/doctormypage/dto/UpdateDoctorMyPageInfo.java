package com.codelion.animalcare.domain.doctormypage.dto;

import com.codelion.animalcare.domain.user.entity.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UpdateDoctorMyPageInfo {
    @Getter
    @Setter
    public static class RequestDto{
        private String loginEmail;

        private String name;

        private LocalDateTime birthday;

        private String major;

        private String phoneNum;

        private String introduce;

        private int genderId;
        public Doctor toEntity(Doctor doctor){
            return Doctor.builder()
                    .id(doctor.getId())
                    .email(loginEmail)
                    .password(doctor.getPassword())
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
