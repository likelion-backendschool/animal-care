package com.codelion.animalcare.domain.animal.entity;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.codelion.animalcare.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animal extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime birthday;

    @Column(nullable = false, length = 100)
    private String registration_num;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int gender_id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    // == 연관관계 메서드 == //
    public void addMember(Member member) {
        this.member = member;
        member.getAnimals().add(this);
    }

    @Builder
    private Animal(Long id, LocalDateTime createdAt, String name, LocalDateTime birthday, String registration_num, LocalDateTime deletedAt, int gender_id, Member member) {
        super(id, createdAt);
        this.name = name;
        this.birthday = birthday;
        this.registration_num = registration_num;
        this.deletedAt = deletedAt;
        this.gender_id = gender_id;
        this.member = member;
    }


    // Animal : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();


}
