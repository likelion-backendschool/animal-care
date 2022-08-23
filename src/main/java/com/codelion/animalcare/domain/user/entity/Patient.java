package com.codelion.animalcare.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
// TODO : 기존 코드에 영향을 안 줄려고 Patient로 선언함 추후 수정 해야함, SigleTable 전략을 씀, animal은 테스트용 컬럼
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends UserInfo{

    @Column
    private String animal;

    @Builder(builderMethodName = "patientBuilder")
    public Patient(String email, String password, String auth, String animal) {
        super(email, password, auth);
        this.animal = animal;
    }
}
