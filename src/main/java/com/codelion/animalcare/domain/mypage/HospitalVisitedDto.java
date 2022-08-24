package com.codelion.animalcare.domain.mypage;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class HospitalVisitedDto {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;
}
