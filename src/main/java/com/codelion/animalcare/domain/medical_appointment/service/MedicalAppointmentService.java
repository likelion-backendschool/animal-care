package com.codelion.animalcare.domain.medical_appointment.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.repository.AnimalRepository;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.repository.DoctorRepository;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.repository.HospitalRepository;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepository;
import com.codelion.animalcare.domain.member.entity.Member;
import com.codelion.animalcare.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalAppointmentService {

    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final MemberRepository memberRepository;
    private final AnimalRepository animalRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;


    public List<MedicalAppointment> findByDoctorId(long id) {
        return medicalAppointmentRepository.findByDoctorId(id);
    }

    public List<MedicalAppointment> findByMemberId(long id) {
        return medicalAppointmentRepository.findByMemberId(id);
    }


    /**
     * 예약
     */
    @Transactional
    public Long medicalAppointment(Long memberId, Long animalId, Long hospitalId, Long doctorId, LocalDateTime medicalAppointmentDate) {

        //엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Animal animal = animalRepository.findById(animalId).get();
        Hospital hospital = hospitalRepository.findById(hospitalId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();


        //예약 생성
        MedicalAppointment medicalAppointment = MedicalAppointment.createMedicalAppointment(member, animal, hospital, doctor, medicalAppointmentDate);

        medicalAppointmentRepository.save(medicalAppointment);

        return medicalAppointment.getId();
    }


    /**
     * 예약 취소
     */
    @Transactional
    public void cancelMedicalAppointment(Long medicalAppointmentId) {
        //예약 엔티티 조회
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.findById(medicalAppointmentId).get();

        //에약 취소
        medicalAppointment.cancel();
    }



}
