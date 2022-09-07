package com.codelion.animalcare.domain.user.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.sql.Date;

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
    private String detail;

    private String phoneNum;

    private Integer genderId;

    private String major;

    private String introduce;

    private String auth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Hospital hospital;

    private Double latitude; // 위도

    private Double longitude; // 경도

    public Doctor toEntity(DoctorSignUpDto doctorDto){
        Address address = new Address(
                doctorDto.getCity(), doctorDto.getStreet(),
                doctorDto.getZipcode(), doctorDto.getDetail(),
                doctorDto.getLatitude(),doctorDto.getLongitude());
        return Doctor.builder()
                .email(doctorDto.getEmail())
                .password(doctorDto.getPassword())
                .name(doctorDto.getName())
                .birthday((java.sql.Date) doctorDto.getBirthDay())
                .address(address)
                .phoneNum(doctorDto.getPhoneNum())
                .genderId(doctorDto.getGenderId())
                .major(doctorDto.getMajor())
                .introduce(doctorDto.getIntroduce())
                .auth("ROLE_DOCTOR")
                .build();
    }
}
