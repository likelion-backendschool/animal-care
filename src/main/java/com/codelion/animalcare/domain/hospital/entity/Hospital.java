package com.codelion.animalcare.domain.hospital.entity;

import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Hospital extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 70)
    @Embedded
    private Address address;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(length = 300)
    private String openingHours;

    @Column()
    private LocalDateTime deletedAt;

    // Hospital : Doctor = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors = new ArrayList<>();

    // Animal : MedicalAppointment = 1: n;
    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Appointment> appointments = new ArrayList<>();
}
