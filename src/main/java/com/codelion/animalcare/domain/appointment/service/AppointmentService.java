package com.codelion.animalcare.domain.appointment.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.repository.AnimalRepository;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.repository.HospitalRepository;
import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
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
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final MemberRepository memberRepository;
    private final AnimalRepository animalRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;


    public List<LoadMyPageDoctorAppointment.ResponseDto> findAllByDoctorEmail(String email) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor " + email + "is not found."));

        List<Appointment> appointmentList = appointmentRepository.findAllByDoctorId(doctor.getId());

        List< LoadMyPageDoctorAppointment.ResponseDto> result = appointmentList.stream()
                .map(LoadMyPageDoctorAppointment.ResponseDto::new).toList();

        return result;
    }

    public List<Appointment> findAppointments() {
        return appointmentRepository.findAllAppointments();
    }

    public List<Appointment> findByMemberId(long id) {
        return appointmentRepository.findByMemberId(id);
    }


    /**
     * 예약
     */
    @Transactional
    public Long appointment(Long memberId, Long animalId, Long hospitalId, Long doctorId, LocalDateTime appointmentDate) {

        //엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Animal animal = animalRepository.findById(animalId).get();
        Hospital hospital = hospitalRepository.findById(hospitalId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();

        //예약 생성
        Appointment appointment = Appointment.createAppointment(member, animal, hospital, doctor, appointmentDate);

        appointmentRepository.save(appointment);

        return appointment.getId();
    }


    /**
     * 예약 취소
     */
    @Transactional
    public void cancelAppointment(Long appointmentId) {
        //예약 엔티티 조회
        Appointment appointment = appointmentRepository.findById(appointmentId).get();

        //에약 취소
        appointment.cancel();
    }

    /**
     * 예약 거절
     * @param appointmentId 예약서id
     * @param status 상태
     */
    @Transactional
    public void updateAppointmentStatus(Long appointmentId, AppointmentStatus status) {
        // 예약 엔티티 조회
        Appointment appointment = appointmentRepository.getReferenceById(appointmentId);
        // 예약 변경.
        appointment.updateStatus(status);
    }

    @Transactional(readOnly = true)
    public LoadMyPageDoctorAppointment.ResponseDto findById(long appointmentId) {
        Appointment appointment = appointmentRepository
                .findByIdWithMemberAndAnimalAndHospitalAndDoctorAndDiagnosis(appointmentId)
                        .orElseThrow(() -> new RuntimeException("Appointment id " + appointmentId + " is not found."));

        return new LoadMyPageDoctorAppointment.ResponseDto(appointment);
    }


    public Optional<Member> findMemberByMemberId(Long memberId) {
        return appointmentRepository.findMemberByMemberId(memberId);
    }
}