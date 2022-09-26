package com.codelion.animalcare.domain.appointment.service;

import com.codelion.animalcare.domain.appointment.AppointmentSearch;
import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.user.dto.MemberDto;
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

    public List<AppointmentDto> findAppointmentByMemberDto(MemberDto memberDto) {

        List<Appointment> appointments = appointmentRepository.findByMemberId(memberDto.getId());

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        return appointmentDtos;
    }

    public List<AppointmentDto> findAllAppointment(LoadDoctorMyPageInfo.ResponseDto doctorDto) {

        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorDto.getId());

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        return appointmentDtos;
    }

}
