package com.codelion.animalcare.domain.diagnosis.entity;

import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.codelion.animalcare.domain.animal.entity.Animal;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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
    @Column(nullable = false, length=70)
    private String breedingPlace;

    // 동물의 표시
    // 종류
    @Column(nullable = false, length=50)
    private String animalType;
    // 품종
    @Column(nullable = false, length=50)
    private String animalBreed;
    // 동물명
    @Column(nullable = false, length=50)
    private String animalName;
    // 성별
    @Column(nullable = false, length=50)
    private short animalGenderId;
    // 연령
    @Column(nullable = false, length=50)
    private short animalAge;
    // 모색
    @Column(nullable = false, length=50)
    private String animalCoatColor;
    // 특징
    @Column(nullable = false, length=200)
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
    // 발병 연월일
    @Column(nullable = false)
    private LocalDateTime diseaseDate;
    // 진단 연월일
    @Column(nullable = false)
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
    @Column(nullable = false, length = 50)
    private String doctorLicense;
    // 수의사 이름
    @Column(nullable=false)
    private String doctorName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public static Diagnosis createDiagnosis(FindOneDiagnosis findOneDiagnosis) {

        Diagnosis diagnosis = new Diagnosis();

        diagnosis.setMemberName(findOneDiagnosis.getMemberName());
        diagnosis.setAddressCity(findOneDiagnosis.getAddressCity());
        diagnosis.setAddressStreet(findOneDiagnosis.getAddressStreet());

        diagnosis.setBreedingPlace(findOneDiagnosis.getBreedingPlace());
        diagnosis.setAnimalType(findOneDiagnosis.getAnimalType());
        diagnosis.setAnimalBreed(findOneDiagnosis.getAnimalBreed());
        diagnosis.setAnimalName(findOneDiagnosis.getAnimalName());
        diagnosis.setAnimalGenderId(findOneDiagnosis.getAnimalGenderId());
        diagnosis.setAnimalAge(findOneDiagnosis.getAnimalAge());
        diagnosis.setAnimalCoatColor(findOneDiagnosis.getAnimalCoatColor());
        diagnosis.setAnimalSpecial(findOneDiagnosis.getAnimalSpecial());
        diagnosis.setDiseaseName(findOneDiagnosis.getDiseaseName());

        diagnosis.setDiseaseDate(findOneDiagnosis.getDiseaseDate());
        diagnosis.setDiagnosisDate(findOneDiagnosis.getDiagnosisDate());
        diagnosis.setOpinion(findOneDiagnosis.getOpinion());
        diagnosis.setOtherMatter(findOneDiagnosis.getOtherMatter());

        diagnosis.setHospitalName(findOneDiagnosis.getHospitalName());
        diagnosis.setHospitalStreet(findOneDiagnosis.getHospitalStreet());
        diagnosis.setDoctorLicense(findOneDiagnosis.getDoctorLicense());
        diagnosis.setDoctorName(findOneDiagnosis.getDoctorName());

        return diagnosis;
    }
}
