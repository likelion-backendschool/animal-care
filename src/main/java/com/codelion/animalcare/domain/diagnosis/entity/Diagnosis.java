package com.codelion.animalcare.domain.diagnosis.entity;

import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.codelion.animalcare.domain.animal.entity.Animal;
import groovy.lang.Lazy;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Setter
public class Diagnosis extends BaseEntity{
    // 동물 보호자
    // 성명
    @Column(nullable = false, length = 30)
    private String memberName;
    // 주소
    @Column(nullable = false, length=70)
    private String addressCity;
    @Column(nullable = false, length=70)
    private String addressStreet;

    //사육 장소
    //    nullable = true로 잠시 수정
    @Column(nullable = true, length=70)
    private String breedingPlace;

    // 동물의 표시
    // 종류
    //    nullable = true로 잠시 수정
    @Column(nullable = true, length=50)
    private String animalType;
    // 품종
//    nullable = true로 잠시 수정
    @Column(nullable = true, length=50)
    private String animalBreed;
    // 동물명
    @Column(nullable = false, length=50)
    private String animalName;
    // 성별
    @Column(nullable = false, length=50)
    private int animalGenderId;
    // 연령
    //    nullable = true로 잠시 수정
    @Column(nullable = true, length=50)
    private int animalAge;
    // 모색
    //    nullable = true로 잠시 수정
    @Column(nullable = true, length=50)
    private String animalCoatColor;
    // 특징
    //    nullable = true로 잠시 수정
    @Column(nullable = true, length=200)
    private String animalSpecial;

    // 병명
    @Column(nullable = false, length=50)
    private String diseaseName;

//    // 발병 연월일
//    @Column(nullable = false)
//    private Date diseaseDate;
//    // 진단 연월일
//    @Column(nullable = false)
//    private Date diagnosisDate;

//    임시로 바꿔봄 Date -> LocalDateTime
//    잠시 nullable = true
    // 발병 연월일
    @Column(nullable = true)
    private LocalDateTime diseaseDate;
    // 진단 연월일
    @Column(nullable = true)
    private LocalDateTime diagnosisDate;


    // 예후 소견
    @Column(columnDefinition = "TEXT",nullable = false)
    private String opinion;
    // 그 밖의 사항
    @Column(columnDefinition = "TEXT",nullable = false)
    private String otherMatter;

    // 동물병언 명칭
    @Column(nullable = false, length = 20)
    private String hospitalName;
    // 동물병원 주소
    @Column(nullable = false, length = 70)
    private String hospitalStreet;
    // 수의사 면허
    //    nullable = true로 잠시 수정
    @Column(nullable = true, length = 50)
    private String doctorLicense;
    // 수의사 이름
    @Column(nullable=false)
    private String doctorName;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    // == 연관관계 메서드 == //
    public void addMember(Member member) {
        this.member = member;
        member.getDiagnoses().add(this);
    }

    public void addAnimal(Animal animal) {
        this.animal = animal;
        animal.getDiagnoses().add(this);
    }

    public void addDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getDiagnoses().add(this);
    }

    public void addHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getDiagnoses().add(this);
    }

    public void addAppointment(Appointment appointment) {
        this.appointment = appointment;
    }


    public static Diagnosis createDiagnosis(Member member, Animal animal, Hospital hospital, Doctor doctor, Appointment appointment, FindOneDiagnosis writtenDiagnosisForm) {

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.addMember(member);
        diagnosis.addAnimal(animal);
        diagnosis.addHospital(hospital);
        diagnosis.addDoctor(doctor);
        diagnosis.addAppointment(appointment);

        diagnosis.setMemberName(member.getName());
        diagnosis.setAddressCity(member.getAddress().getCity());
        diagnosis.setAddressStreet(member.getAddress().getStreet());

        diagnosis.setBreedingPlace(writtenDiagnosisForm.getBreedingPlace());
        diagnosis.setAnimalType(writtenDiagnosisForm.getAnimalType());
        diagnosis.setAnimalBreed(writtenDiagnosisForm.getAnimalBreed());

        diagnosis.setAnimalName(animal.getName());
        diagnosis.setAnimalGenderId(animal.getGenderId());

        diagnosis.setAnimalAge(writtenDiagnosisForm.getAnimalAge());
        diagnosis.setAnimalCoatColor(writtenDiagnosisForm.getAnimalCoatColor());
        diagnosis.setAnimalSpecial(writtenDiagnosisForm.getAnimalSpecial());
        diagnosis.setDiseaseName(writtenDiagnosisForm.getDiseaseName());

        diagnosis.setDiseaseDate(writtenDiagnosisForm.getDiseaseDate());
        diagnosis.setDiagnosisDate(writtenDiagnosisForm.getDiagnosisDate());
        diagnosis.setOpinion(writtenDiagnosisForm.getOpinion());
        diagnosis.setOtherMatter(writtenDiagnosisForm.getOtherMatter());

        diagnosis.setHospitalName(hospital.getName());
        diagnosis.setHospitalStreet(hospital.getAddress().getStreet());

        diagnosis.setDoctorLicense(writtenDiagnosisForm.getDoctorLicense());

        diagnosis.setDoctorName(doctor.getName());

        return diagnosis;
    }
}
