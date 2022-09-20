package com.codelion.animalcare.domain.diagnosis.dto;

import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class FindOneDiagnosis {
    // 동물 보호자
    // 성명
    private String memberName;
    // 주소
    private String addressCity;
    private String addressStreet;

    //사육 장소
    private String breedingPlace;

    // 동물의 표시
    // 종류
    private String animalType;
    // 품종
    private String animalBreed;
    // 동물명
    private String animalName;
    // 성별
    private short animalGenderId;
    // 연령
    private short animalAge;
    // 모색
    private String animalCoatColor;
    // 특징
    private String animalSpecial;

    // 병명
    private String diseaseName;
    // 발병 연월일
    private Date diseaseDate;
    // 진단 연월일
    private Date diagnosisDate;
    // 예후 소견
    private String opinion;
    // 그 밖의 사항
    private String otherMatter;

    // 동물병언 명칭
    private String hospitalName;
    // 동물병원 주소
    private String hospitalStreet;
    // 수의사 면허
    private String doctorLicense;
    // 수의사 이름
    private String doctorName;

    private Appointment appointment;

    public FindOneDiagnosis(Diagnosis diagnosis) {
        this.memberName = diagnosis.getMemberName();
        this.addressCity = diagnosis.getAddressCity();
        this.addressStreet = diagnosis.getAddressStreet();
        this.breedingPlace = diagnosis.getBreedingPlace();
        this.animalType = diagnosis.getAnimalType();
        this.animalBreed = diagnosis.getAnimalBreed();
        this.animalName = diagnosis.getAnimalName();
        this.animalGenderId = diagnosis.getAnimalGenderId();
        this.animalAge = diagnosis.getAnimalAge();
        this.animalCoatColor = diagnosis.getAnimalCoatColor();
        this.animalSpecial = diagnosis.getAnimalSpecial();
        this.diseaseName = diagnosis.getDiseaseName();
        this.diseaseDate = diagnosis.getDiseaseDate();
        this.diagnosisDate = diagnosis.getDiagnosisDate();
        this.opinion = diagnosis.getOpinion();
        this.otherMatter = diagnosis.getOtherMatter();
        this.hospitalName = diagnosis.getHospitalName();
        this.hospitalStreet = diagnosis.getHospitalStreet();
        this.doctorLicense = diagnosis.getDoctorLicense();
        this.doctorName = diagnosis.getDoctorName();
        this.appointment = diagnosis.getAppointment();
    }
}
