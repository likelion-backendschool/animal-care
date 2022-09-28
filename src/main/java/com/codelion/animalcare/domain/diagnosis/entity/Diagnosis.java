package com.codelion.animalcare.domain.diagnosis.entity;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static javax.persistence.FetchType.LAZY;

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

    // 발병 연월일
    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date diseaseDate;

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
    //    nullable = true로 잠시 수정
    @Column(nullable = false, length = 50)
    private String doctorLicense;
    // 수의사 이름
    @Column(nullable=false)
    private String doctorName;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "doctor_id"
//    private Doctor doctor;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "hospital_id")
//    private Hospital hospital;


    // == 연관관계 메서드 == //
//    public void addDoctor(Doctor doctor) {
//        this.doctor = doctor;
//        doctor.getDiagnoses().add(this);
//    }
//
//    public void addHospital(Hospital hospital) {
//        this.hospital = hospital;
//        hospital.getDiagnoses().add(this);
//    }

    public void addAppointment(Appointment appointment) {
        this.appointment = appointment;
    }


    public static Diagnosis createDiagnosis(Member member, Animal animal, Hospital hospital, Doctor doctor, Appointment appointment, FindOneDiagnosis writtenDiagnosisForm) {

        Diagnosis diagnosis = new Diagnosis();
//        diagnosis.addHospital(hospital);
//        diagnosis.addDoctor(doctor);
        diagnosis.addAppointment(appointment);

        diagnosis.setMemberName(member.getName());
        diagnosis.setAddressCity(member.getAddress().getCity());
        diagnosis.setAddressStreet(member.getAddress().getStreet());

        diagnosis.setAnimalName(animal.getName());
        diagnosis.setAnimalGenderId(animal.getGenderId());
        diagnosis.setBreedingPlace(animal.getBreedingPlace());
        diagnosis.setAnimalType(animal.getAnimalType());
        diagnosis.setAnimalBreed(animal.getAnimalBreed());

        int animalAge = getAgeFromBirthday(animal.getBirthday());
        diagnosis.setAnimalAge(animalAge);

        diagnosis.setHospitalName(hospital.getName());
        diagnosis.setHospitalStreet(hospital.getAddress().getStreet());

        diagnosis.setDoctorName(doctor.getName());
        diagnosis.setDoctorLicense(doctor.getDoctorLicense());

        appointment.setStatus(AppointmentStatus.COMPLETE);
        diagnosis.setDiagnosisDate(appointment.getDate());


        diagnosis.setAnimalCoatColor(animal.getAnimalCoatColor());
        diagnosis.setAnimalSpecial(animal.getAnimalSpecial());

        diagnosis.setDiseaseName(writtenDiagnosisForm.getDiseaseName());
        diagnosis.setDiseaseDate(writtenDiagnosisForm.getDiseaseDate());

        diagnosis.setOpinion(writtenDiagnosisForm.getOpinion());
        diagnosis.setOtherMatter(writtenDiagnosisForm.getOtherMatter());


        return diagnosis;
    }

    public static int getAgeFromBirthday(Date birthday) {
          Calendar birth = new GregorianCalendar();
          Calendar today = new GregorianCalendar();

          birth.setTime(birthday);
          today.setTime(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        int factor = 0;
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            factor = -1;
        }

        return today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + factor;
    }
}
