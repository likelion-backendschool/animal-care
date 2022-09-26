package com.codelion.animalcare.domain.user.entity;

import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO : 기존 코드에 영향을 안 줄려고 DoctorLogin로 선언함 추후 수정 해야함, SigleTable 전략을 씀
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Doctor extends UserInfo{

    @Column()
    private String major;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Column(columnDefinition = "TEXT")
    private String introduce;
// TODO : deleted flag 추가 하는 방향으로 바꾸기
//    @Column()
//    private LocalDateTime deletedAt;

    public void updateLoginPwd(String newLoginPwd){
        password = newLoginPwd;
    }

    // Doctor : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments = new ArrayList<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "doctor")
//    private List<Diagnosis> diagnoses = new ArrayList<>();

    public void addHospital(Hospital hospital){
        this.hospital = hospital;
    }
}
