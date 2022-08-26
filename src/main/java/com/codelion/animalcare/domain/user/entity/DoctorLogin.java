package com.codelion.animalcare.domain.user.entity;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// TODO : 기존 코드에 영향을 안 줄려고 DoctorLogin로 선언함 추후 수정 해야함, SigleTable 전략을 씀, 구현 후 필요한 컬럼 모두 추가 예정
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DoctorLogin extends UserInfo{

    @Column()
    private String major;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

//    @Builder(builderMethodName = "doctorLoginBuilder")
//    public DoctorLogin(String email, String password, String auth, String major, Hospital hospital) {
//        super(email, password, auth);
//        this.major = major;
//        this.hospital = hospital;
//    }
}
