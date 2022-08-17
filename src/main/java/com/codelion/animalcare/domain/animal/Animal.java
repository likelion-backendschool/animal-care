package com.codelion.animalcare.domain.animal;

import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Animal {

    @Id // 기본 키 직접 할
    @GeneratedValue // 자동 생성 전략 사용
    @Column(name = "animal_id")
    private Long id;

    String name;

    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private Date birthday;

    String registration_num;

    // 자바의 enum을 사용하기 위해 @Enumerated 어노테이션으로 매핑함
//    @Enumerated(EnumType.STRING)
//    private AnimalHealthStatus animalHealthStatus; // 건강상태 [GOOD, BAD] 좋음, 나쁨


    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;


    // Animal : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    private List<MedicalAppointment> medicalAppointments = new ArrayList<>();


}
