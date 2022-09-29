package com.codelion.animalcare.domain.animal.entity;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
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

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Animal extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;

    @Column(nullable = false, length = 100)
    private String registrationNum;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;

    // 추가한 컬럼

    @Column(nullable = true, length=70)
    private String breedingPlace;

    @Column()
    private String animalType;

    @Column()
    private String animalBreed;

    @Column()
    private String animalCoatColor;

    @Column(nullable = true, length=200)
    private String animalSpecial;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    // == 연관관계 메서드 == //
    public void addMember(Member member) {
        this.member = member;
        member.getAnimals().add(this);
    }


    // Animal : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<Appointment> appointments = new ArrayList<>();

    public void update(AnimalDto animalDto){
        name = animalDto.getName();
        registrationNum = animalDto.getRegistrationNum();
        birthday = animalDto.getBirthday();
        genderId = animalDto.getGenderId();
    }
}
