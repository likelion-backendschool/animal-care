package com.codelion.animalcare.domain.doctorTmp;


import com.codelion.animalcare.domain.hospitalTmp.Hospital;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

/**
 * 임시 만듦
 */
@Entity
@Getter
@Setter
public class Doctor {

    @Id // 기본 키 직접 할
    @GeneratedValue // 자동 생성 전략 사용
    @Column(name = "doctor_id")
    private Long id;

    private String login_email;
    private String login_pwd;
    private String name;

    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String major;

    private String phone_num;


    // @Lob: 예약하는 content에 대해서 필드 길이 제한을 두지 않음
    // 데이터베이스 VARCHAR 타입이 아닌 CLOB 타입으로 저장해야함
    // @Lob를 사용하면 CLOB, BLOB 타입을 매핑할 수 있음
    @Lob
    private String introduce;


    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;


    // Doctor : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();


    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

}
