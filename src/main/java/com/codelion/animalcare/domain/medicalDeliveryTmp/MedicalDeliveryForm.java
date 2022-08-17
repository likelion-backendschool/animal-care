package com.codelion.animalcare.domain.medicalDeliveryTmp;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
/**
 * 임시 만듦
 */
@Getter
@Setter
public class MedicalDeliveryForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;
}
