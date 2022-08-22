package com.codelion.animalcare.domain.member.entity;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.member.Address;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Column(nullable = false, length = 50, unique = true)
    private String loginEmail;

    @Column(nullable = false, length = 50)
    private String loginPwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime birthday;

    @Column(nullable = false, length = 70)
    @Embedded
    private Address address;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;

    @Builder
    private Member(Long id, LocalDateTime createdAt, String loginEmail, String loginPwd, String name, LocalDateTime birthday, Address address, String phoneNum, LocalDateTime deletedAt, int genderId) {
        super(id, createdAt);
        this.loginEmail = loginEmail;
        this.loginPwd = loginPwd;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.phoneNum = phoneNum;
        this.deletedAt = deletedAt;
        this.genderId = genderId;
    }

    // Member : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();


}
