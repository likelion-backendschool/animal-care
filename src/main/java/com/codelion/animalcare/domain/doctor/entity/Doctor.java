package com.codelion.animalcare.domain.doctor.entity;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doctor extends BaseEntity {
    @Column(nullable = false, length = 50, unique = true)
    private String loginEmail;

    @Column(nullable = false, length = 50)
    private String loginPwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime birthday;

    @Column(nullable = false, length = 30)
    private String major;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


    // 추가: Doctor, MedicalAppointment 관계
    // Doctor : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();


    public void updateLoginPwd(String newLoginPwd){
        loginPwd = newLoginPwd;
    }

    @Builder
    private Doctor(Long id, LocalDateTime createdAt, String loginEmail, String loginPwd, String name, LocalDateTime birthday, String major, String phoneNum, String introduce, LocalDateTime deletedAt, int genderId, Hospital hospital) {
        super(id, createdAt);
        this.loginEmail = loginEmail;
        this.loginPwd = loginPwd;
        this.name = name;
        this.birthday = birthday;
        this.major = major;
        this.phoneNum = phoneNum;
        this.introduce = introduce;
        this.deletedAt = deletedAt;
        this.genderId = genderId;
        this.hospital = hospital;
    }
}
