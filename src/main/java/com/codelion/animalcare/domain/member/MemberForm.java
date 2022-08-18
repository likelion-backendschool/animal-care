package com.codelion.animalcare.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    private String login_email;
    private String login_pwd;

    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private Date birthday;

//    어떻게 쓸지
//    @Embedded
//    private Address address;

    private String city;
    private String street;
    private String zipcode;


    private String phone_num;

}
