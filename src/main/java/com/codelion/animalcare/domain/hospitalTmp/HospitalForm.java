package com.codelion.animalcare.domain.hospitalTmp;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
public class HospitalForm {

    @NotEmpty(message = "병원 이름은 필수 입니다")
    private String name;


}
