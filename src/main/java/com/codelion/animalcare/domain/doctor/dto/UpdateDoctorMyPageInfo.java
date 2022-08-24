package com.codelion.animalcare.domain.doctor.dto;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
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
        private Date birthday;
        @NotEmpty(message = "전공을 작성하여야 합니다.")
        private String major;
        @NotEmpty(message = "핸드폰 번호를 작성하여야 합니다.")
        private String phoneNum;
        private String introduce;
        @NotNull(message = "성별을 작성하여야 합니다.")
        private Integer genderId;

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
                    .hospital(doctor.getHospital())
                    .createdAt(doctor.getCreatedAt())
                    .build();
        }
    }
}
