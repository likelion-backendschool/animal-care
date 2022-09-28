package com.codelion.animalcare.domain.appointment.service;

import com.codelion.animalcare.domain.appointment.AppointmentSearch;
import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.mypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.user.dto.MemberDto;
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

    public List<AppointmentDto> findAppointmentByEmail(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member " + email + "is not found."));

        List<Appointment> appointments = appointmentRepository.findByMemberId(member.getId());

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        return appointmentDtos;
    }

    public List<AppointmentDto> findAllAppointment(String email) {

        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor " + email + "is not found."));

        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctor.getId());

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        return appointmentDtos;
    }
}
