package com.codelion.animalcare.domain.appointment.service;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;
    private final MemberRepository memberRepository;
    private final DoctorRepository doctorRepository;


    /**
     * 비대면 진료
     * Doctor가 예약내역 확인
     */
    public List<AppointmentDto> findAllAppointment(String email) {

        Doctor doctor = findDoctor(email);

        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctorId(doctor.getId());

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        return appointmentDtos;
    }


    /**
     * DoctorMyPage
     * 환자 예약  관리
     */
    public List<LoadMyPageDoctorAppointment.ResponseDto> findAllByDoctorEmail(String email) {

        Doctor doctor = findDoctor(email);

        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByDoctorId(doctor.getId());

        List< LoadMyPageDoctorAppointment.ResponseDto> result = appointmentList.stream()
                .map(LoadMyPageDoctorAppointment.ResponseDto::new).toList();

        return result;
    }




    public List<AppointmentDto> findAppointmentByEmail(String email) {

        Member member = findMember(email);

        List<Appointment> appointments = appointmentRepository.findByMemberId(member.getId());

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        return appointmentDtos;
    }


    private Doctor findDoctor(String email) {
        return doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor " + email + "is not found."));
    }

    private Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member " + email + "is not found."));
    }
}
