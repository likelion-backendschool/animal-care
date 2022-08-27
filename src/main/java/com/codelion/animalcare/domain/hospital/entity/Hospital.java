package com.codelion.animalcare.domain.hospital.entity;


import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

import javax.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Hospital extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    /*@Column(nullable = false, length = 70)
    @Embedded
    private Address address;*/

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(length = 300)
    private String openingHours;

    @Column()
    private LocalDateTime deletedAt;

//    @Builder
//    private Hospital(Long id, LocalDateTime createdAt, String name/* Address address*/, String phoneNum, String openingHours, LocalDateTime deletedAt) {
//        super(id, createdAt);
//        this.name = name;
//        /*this.address = address;*/
//        this.phoneNum = phoneNum;
//        this.openingHours = openingHours;
//        this.deletedAt = deletedAt;
//    }

    // TODO : 엔터티(예약 - 병원 - 닥터) 연결순서로 인해 나중에 수정해야함
//    // Hospital : MedicalAppointment = 1: n;
//    @JsonIgnore
//    @OneToMany(mappedBy = "hospital")
//    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();
//
//
//    // Hospital : Doctor = 1: n;
//    @JsonIgnore
//    @OneToMany(mappedBy = "hospital")
//    private List<Doctor> doctors = new ArrayList<>();


}
