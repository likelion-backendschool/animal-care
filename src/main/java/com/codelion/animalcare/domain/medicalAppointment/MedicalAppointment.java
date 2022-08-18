package com.codelion.animalcare.domain.medicalAppointment;

import com.codelion.animalcare.domain.animal.Animal;
import com.codelion.animalcare.domain.doctorTmp.Doctor;
import com.codelion.animalcare.domain.hospitalTmp.Hospital;
import com.codelion.animalcare.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;


@Entity
@Getter @Setter
public class MedicalAppointment {

    @Id
    @GeneratedValue
    @Column(name = "medicalAppointment_id")
    private Long id;

    // 필요없어보임1
//    private String diagnosis_name;

    // @Lob: 예약하는 content에 대해서 필드 길이 제한을 두지 않음
    // 데이터베이스 VARCHAR 타입이 아닌 CLOB 타입으로 저장해야함
    // @Lob를 사용하면 CLOB, BLOB 타입을 매핑할 수 있음

    // 필요없어보임2
//    @Lob
//    private String content;


    // 자바의 enum을 사용하기 위해 @Enumerated 어노테이션으로 매핑함
    @Enumerated(EnumType.STRING)
    private MedicalAppointmentStatus medicalAppointmentStatus; // 예약상태 [COMPLETE, CANCEL] 완료, 취소


    private LocalDateTime medicalAppointmentDate; // 예약날짜 및 시간


    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;



    // MedicalAppointment : Member = n : 1;
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 예약 회원

    // MedicalAppointment : Animal = n : 1;
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal; // 예약 애완동물


    // MedicalAppointment : Doctor = n : 1;
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor; // 예약 닥터


    // MedicalAppointment : Hospital = n : 1;
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital; // 예약 병원


    // == 연관관계 메서드 == //
    public void setMember(Member member) {
        this.member = member;
        member.getMedicalAppointments().add(this);
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
        animal.getMedicalAppointments().add(this);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getMedicalAppointments().add(this);
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getMedicalAppointments().add(this);
    }


    //== 생성 메서드 ==//
    public static MedicalAppointment createMedicalAppointment(Member member, Animal animal, Hospital hospital, Doctor doctor) {
        MedicalAppointment medicalAppointment = new MedicalAppointment();
        medicalAppointment.setMember(member);
        medicalAppointment.setAnimal(animal);
        medicalAppointment.setHospital(hospital);
        medicalAppointment.setDoctor(doctor);

        medicalAppointment.setMedicalAppointmentStatus(MedicalAppointmentStatus.COMPLETE);
        medicalAppointment.setMedicalAppointmentDate(LocalDateTime.now());
        return medicalAppointment;
    }


    //==비즈니스 로직==//
    /**
     * 예약 취소
     */
    public void cancel() {
        this.setMedicalAppointmentStatus(MedicalAppointmentStatus.CANCEL);
    }
}
