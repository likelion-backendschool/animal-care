package com.codelion.animalcare.domain.delivery;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class DeliveryDto {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;
}
