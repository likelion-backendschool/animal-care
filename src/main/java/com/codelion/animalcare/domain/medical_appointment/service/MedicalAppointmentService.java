package com.codelion.animalcare.domain.medical_appointment.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.repository.AnimalRepository;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.repository.HospitalRepository;
import com.codelion.animalcare.domain.medical_appointment.MedicalAppointmentStatus;
import com.codelion.animalcare.domain.medical_appointment.dto.LoadMyPageDoctorMedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepository;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalAppointmentService {

    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final MemberRepository memberRepository;
    private final AnimalRepository animalRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;


    public List<LoadMyPageDoctorMedicalAppointment.ResponseDto> findAllByDoctorEmail(String email) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor " + email + "is not found."));

        List<MedicalAppointment> medicalAppointmentList = medicalAppointmentRepository.findAllByDoctorId(doctor.getId());

        List< LoadMyPageDoctorMedicalAppointment.ResponseDto> result =medicalAppointmentList.stream()
                .map(LoadMyPageDoctorMedicalAppointment.ResponseDto::new).toList();

        return result;
    }

    public List<MedicalAppointment> findMedicalAppointments() {
        return medicalAppointmentRepository.findAllWithMemberAnimalHospitalDoctor();
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

    /**
     * 예약 거절
     * @param medicalAppointmentId 예약서id
     * @param status 상태
     */
    @Transactional
    public void updateMedicalAppointmentStatus(Long medicalAppointmentId, MedicalAppointmentStatus status) {
        // 예약 엔티티 조회
        MedicalAppointment medicalAppointment = medicalAppointmentRepository.getReferenceById(medicalAppointmentId);
        // 예약 변경.
        medicalAppointment.updateStatus(status);
    }

    @Transactional(readOnly = true)
    public LoadMyPageDoctorMedicalAppointment.ResponseDto findById(long medicalAppointmentId) {
        MedicalAppointment medicalAppointment = medicalAppointmentRepository
                .findByIdWithMemberAndAnimalAndHospitalAndDoctorAndDiagnosis(medicalAppointmentId)
                        .orElseThrow(() -> new RuntimeException("MedicalAppointment id " + medicalAppointmentId + " is not found."));

        return new LoadMyPageDoctorMedicalAppointment.ResponseDto(medicalAppointment);
    }


    public Optional<Member> findMemberByMemberId(Long memberId) {
        return medicalAppointmentRepository.findMemberByMemberId(memberId);
    }
}
