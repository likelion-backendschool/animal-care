package com.codelion.animalcare.domain.user.entity;

import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Column(columnDefinition = "TEXT")
    private String introduce;
// TODO : deleted flag 추가 하는 방향으로 바꾸기
//    @Column()
//    private LocalDateTime deletedAt;

    @Column()
    private String doctorLicense;

    public void updateLoginPwd(String newLoginPwd){
        password = newLoginPwd;
    }

    // Doctor : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments = new ArrayList<>();

    public void addHospital(Hospital hospital){
        this.hospital = hospital;
        hospital.getDoctors().add(this);
    }
}
