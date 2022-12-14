package com.codelion.animalcare.domain.user.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSignUpDto {

    private Long id;

    @NotEmpty(message = "이메일은 필수항목입니다")
    private String email;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;

//    @NotEmpty(message = "생일은 필수항목입니다.")
    private Date birthDay;

    private String city;
    private String street;
    private String zipcode;
    private String detail;

    private String phoneNum;

    private Integer genderId;

    private String auth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Double latitude; // 위도

    private Double longitude; // 경도

    public Member toEntity(MemberSignUpDto memberSignUpDto){
        Address address = new Address(
                memberSignUpDto.getCity(), memberSignUpDto.getStreet(),
                memberSignUpDto.getZipcode(), memberSignUpDto.getDetail(),
                memberSignUpDto.getLatitude(),memberSignUpDto.getLongitude());
        return Member.builder()
                .email(memberSignUpDto.getEmail())
                .password(memberSignUpDto.getPassword())
                .name(memberSignUpDto.getName())
                .birthday((java.sql.Date) memberSignUpDto.getBirthDay())
                .address(address)
                .phoneNum(memberSignUpDto.getPhoneNum())
                .genderId(memberSignUpDto.getGenderId())
                .auth("ROLE_MEMBER")
                .build();
    }
}
