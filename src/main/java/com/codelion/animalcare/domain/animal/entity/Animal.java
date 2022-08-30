package com.codelion.animalcare.domain.animal.entity;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Animal extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime birthday;

    @Column(nullable = false, length = 100)
    private String registrationNum;

    @Column(length = 45)
    private String health_status;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

//    @Builder
//    private Animal(Long id, LocalDateTime createdAt, String name, LocalDateTime birthday, String registrationNum, String health_status, LocalDateTime deletedAt, int genderId, Member member) {
//        super(id, createdAt);
//        this.name = name;
//        this.birthday = birthday;
//        this.registrationNum = registrationNum;
//        this.health_status = health_status;
//        this.deletedAt = deletedAt;
//        this.genderId = genderId;
//        this.member = member;
//    }


    // Animal : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();

}
