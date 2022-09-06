package com.codelion.animalcare.domain.user.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorSignUpDto {

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

    private String major;

    private String introduce;

    private String auth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Hospital hospital;

    public Doctor toEntity(DoctorSignUpDto doctorDto){
        return Doctor.builder()
                .email(doctorDto.getEmail())
                .password(doctorDto.getPassword())
                .name(doctorDto.getName())
                .birthday((java.sql.Date) doctorDto.getBirthDay())
                .address(new Address(doctorDto.getCity(), doctorDto.getStreet(), doctorDto.getZipcode()))
                .phoneNum(doctorDto.getPhoneNum())
                .genderId(doctorDto.getGenderId())
                .major(doctorDto.getMajor())
                .introduce(doctorDto.getIntroduce())
                .auth("ROLE_DOCTOR")
                .build();
    }
}
