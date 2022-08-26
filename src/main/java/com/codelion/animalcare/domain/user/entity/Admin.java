package com.codelion.animalcare.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
//@NoArgsConstructor
@SuperBuilder
public class Admin extends UserInfo{

//    @Builder(builderMethodName = "adminBuilder")
//    public Admin(String email, String password, String auth, String animal) {
//        super(email, password, auth);
//    }
}
