package com.codelion.animalcare.domain.hospitalTmp;


import com.codelion.animalcare.domain.member.Address;
import com.codelion.animalcare.domain.doctorTmp.Doctor;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 임시 만듦
 */
@Entity
@Getter
@Setter
public class Hospital {


    @Id // 기본 키 직접 할
    @GeneratedValue // 자동 생성 전략 사용
    @Column(name = "hospital_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private String phone_num;

    private String opening_hours;


    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;


    // Hospital : Doctor = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors = new ArrayList<>();


    // Hospital : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();

}
