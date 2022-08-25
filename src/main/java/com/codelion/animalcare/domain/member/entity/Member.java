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

    //잠시 nullable = true로 바꿈
    @Column(nullable = true, length = 70)
    @Embedded
    private Address address;

    @Column(nullable = false, length = 20)
    private String phone_num;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;

    @Builder
    private Member(Long id, LocalDateTime createdAt, String login_email, String login_pwd, String name, LocalDateTime birthday, Address address, String phone_num, LocalDateTime deletedAt, int genderId) {
        super(id, createdAt);
        this.login_email = login_email;
        this.login_pwd = login_pwd;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.phone_num = phone_num;
        this.deletedAt = deletedAt;
        this.genderId = genderId;
    }


    // Member : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();


    // Member : Animal = 1: n;
    @OneToMany(mappedBy = "member")
    private List<Animal> animals = new ArrayList<>();


//   Member에 따른 Animal 구현 실험 위해 잠시 만들었음
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private Member memberParent;

// Member : Animal = 1: n;
//    @JsonIgnore
//    @OneToMany(mappedBy = "memberParent")
//    private List<Member> animalsChildren = new ArrayList<>();





}
