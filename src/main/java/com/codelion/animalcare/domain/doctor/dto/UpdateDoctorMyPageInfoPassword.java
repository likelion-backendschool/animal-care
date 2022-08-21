package com.codelion.animalcare.domain.doctor.dto;

import lombok.Getter;
import lombok.Setter;

public class UpdateDoctorMyPageInfoPassword {
    @Getter
    @Setter
    public static class RequestDto{
        private String beforePassword;

        private String newPassword;

        private String newPasswordConfirm;

    }
}
