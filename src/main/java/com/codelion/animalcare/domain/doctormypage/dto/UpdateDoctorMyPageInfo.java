package com.codelion.animalcare.domain.doctormypage.dto;

import com.codelion.animalcare.domain.user.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

import java.time.LocalDateTime;

public class UpdateDoctorMyPageInfo {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class RequestDto{
        private Long id;
        @NotEmpty(message = "이메일을 작성하여야 합니다.")
        private String loginEmail;
        @NotEmpty(message = "이름을 작성하여야 합니다.")
        private String name;
        @NotNull(message = "생년월일을 작성햐여야 합니다.")
        private LocalDateTime birthday;
        @NotEmpty(message = "전공을 작성하여야 합니다.")
        private String major;
        @NotEmpty(message = "핸드폰 번호를 작성하여야 합니다.")
        private String phoneNum;
        private String introduce;
        @NotNull(message = "성별을 작성하여야 합니다.")
        private Integer genderId;
        private String auth;

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
                    .hospital(doctor.getHospital())
                    .createdAt(doctor.getCreatedAt())
                    .auth(doctor.getAuth())
                    .build();
        }
    }
}
