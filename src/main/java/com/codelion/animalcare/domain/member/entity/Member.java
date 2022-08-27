package com.codelion.animalcare.domain.member.entity;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.member.Address;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {


    @Column(nullable = false, length = 50, unique = true)
    private String login_email;

    @Column(nullable = false, length = 50)
    private String login_pwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime birthday;


    @Column(nullable = false, length = 70)
    @Embedded
    private Address address;

    @Column(nullable = false, length = 20)
    private String phone_num;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int gender_id;

    @Builder
    private Member(Long id, LocalDateTime createdAt, String login_email, String login_pwd, String name, LocalDateTime birthday, Address address, String phone_num, LocalDateTime deletedAt, int gender_id) {
        super(id, createdAt);
        this.login_email = login_email;
        this.login_pwd = login_pwd;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.phone_num = phone_num;
        this.deletedAt = deletedAt;
        this.gender_id = gender_id;
    }


    // Member : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();


    // Member : Animal = 1: n;
    @OneToMany(mappedBy = "member")
    private List<Animal> animals = new ArrayList<>();


}
