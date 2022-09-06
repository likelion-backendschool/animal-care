package com.codelion.animalcare.domain.user.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSignUpDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private Date birthDay;

    private String city;
    private String street;
    private String zipcode;

    private String phoneNum;

    private Integer genderId;

    private String auth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Member toEntity(MemberSignUpDto memberSignUpDto){
        return Member.builder()
                .email(memberSignUpDto.getEmail())
                .password(memberSignUpDto.getPassword())
                .name(memberSignUpDto.getName())
                .birthday((java.sql.Date) memberSignUpDto.getBirthDay())
                .address(new Address(memberSignUpDto.getCity(), memberSignUpDto.getStreet(), memberSignUpDto.getZipcode()))
                .phoneNum(memberSignUpDto.getPhoneNum())
                .genderId(memberSignUpDto.getGenderId())
                .auth("ROLE_MEMBER")
                .build();
    }
}
