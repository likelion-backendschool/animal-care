package com.codelion.animalcare.domain.user.entity;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

// TODO : 기존 코드에 영향을 안 줄려고 Patient로 선언함 추후 수정 해야함, SigleTable 전략을 씀, animal은 테스트용 컬럼
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class Member extends UserInfo{

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Appointment> appointments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Animal> animals = new ArrayList<>();




//    @Builder(builderMethodName = "patientBuilder")
//    public Patient(String email, String password, String auth, String animal) {
////        super(email, password, auth);
//        this.animal = animal;
//    }
}
