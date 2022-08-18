package com.codelion.animalcare.domain.medicalAppointment;

import com.codelion.animalcare.domain.animal.Animal;
import com.codelion.animalcare.domain.animal.AnimalRepository;
import com.codelion.animalcare.domain.doctorTmp.Doctor;
import com.codelion.animalcare.domain.doctorTmp.DoctorRepository;
import com.codelion.animalcare.domain.hospitalTmp.Hospital;
import com.codelion.animalcare.domain.hospitalTmp.HospitalRepository;
import com.codelion.animalcare.domain.member.Member;
import com.codelion.animalcare.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

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

    public List<MedicalAppointment> findMedicalAppointmentsBefore(MedicalAppointmentSearch medicalAppointmentSearch) {

        return medicalAppointmentRepository.findAllByString(medicalAppointmentSearch);
    }

    public List<MedicalAppointment> findMedicalAppointments() {

        return medicalAppointmentRepository.findAllWithMemberAnimalDoctorHospital();
    }


    /**
     * 예약
     */
    @Transactional
    public Long medicalAppointment(Long memberId, Long animalId, long hospitalId, Long doctorId) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Animal animal = animalRepository.findOne(animalId);
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        Doctor doctor = doctorRepository.findOne(doctorId);

        //예약 생성
        MedicalAppointment medicalAppointment = MedicalAppointment.createMedicalAppointment(member, animal, hospital, doctor);

        medicalAppointmentRepository.save(medicalAppointment);

        return medicalAppointment.getId();
    }


    /**
     * 예약 취소
     */
    @Transactional
    public void cancelMedicalAppointment(Long medicalAppointmentId) {
        //예약 엔티티 조회
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.findOne(medicalAppointmentId);
        //에약 취소
        medicalAppointment.cancel();
    }
}
