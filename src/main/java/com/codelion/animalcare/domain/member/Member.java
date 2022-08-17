package com.codelion.animalcare.domain.member;

import com.codelion.animalcare.domain.diagnosis.Diagnosis;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter @Setter
public class Member {

    @Id // 기본 키 직접 할
    @GeneratedValue // 자동 생성 전략 사용
    @Column(name = "member_id")
    private Long id;


    private String login_email;
    private String login_pwd;
    private String name;

    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Embedded
    private Address address;

    private String phone_num;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

//    @JsonIgnore
//    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "gender_id")
//    private Gender gender;


    // Member : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();


    // Member : Diagnosis = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Diagnosis> diagnoses = new ArrayList<>();


}
