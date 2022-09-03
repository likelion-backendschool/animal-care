package com.codelion.animalcare.domain.user.entity;

import com.nimbusds.openid.connect.sdk.claims.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

//    private String login_email;
//    private String login_pwd;

    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
//    @Temporal(TemporalType.DATE)
//    private LocalDateTime birthday;

//    @Embedded
//    private Address address;

    private String city;
    private String street;
    private String zipcode;
    private String detail;


//    private String phone_num;
}
