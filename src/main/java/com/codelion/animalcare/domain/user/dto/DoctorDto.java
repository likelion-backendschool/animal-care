package com.codelion.animalcare.domain.user.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {

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
}
