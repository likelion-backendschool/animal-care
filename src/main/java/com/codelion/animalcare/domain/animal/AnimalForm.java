package com.codelion.animalcare.domain.animal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
public class AnimalForm {

    @NotEmpty(message = "애완동물 이름은 필수 입니다")
    private String name;

    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private Date birthday;

    String registration_num;


}
