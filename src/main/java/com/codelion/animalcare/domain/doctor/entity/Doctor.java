package com.codelion.animalcare.domain.doctor.entity;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
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
public class Doctor extends BaseEntity {
    @Column(nullable = false, length = 50, unique = true)
    private String loginEmail;

    @Column(nullable = false, length = 50)
    private String loginPwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime birthday;

    @Column(nullable = false, length = 30)
    private String major;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public void updateLoginPwd(String newLoginPwd){
        loginPwd = newLoginPwd;
    }


}
