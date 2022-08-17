package com.codelion.animalcare.domain.itemTmp;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 임시 만듦
 */
@Getter
@Setter
public class ItemOrderListForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

}
