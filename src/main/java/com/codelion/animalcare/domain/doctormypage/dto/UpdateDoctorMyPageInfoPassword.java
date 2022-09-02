package com.codelion.animalcare.domain.doctormypage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateDoctorMyPageInfoPassword {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class RequestDto{
        @NotEmpty(message="기존 비밀번호를 입력하셔야 합니다.")
        @Size(max=50)
        private String beforePassword;

        @NotEmpty(message="새 비밀번호를 입력하셔야 합니다.")
        @Size(max=50)
        private String newPassword;

        @NotEmpty(message="새 비밀번호 재입력을 입력하셔야 합니다.")
        @Size(max=50)
        private String newPasswordConfirm;
    }
}
