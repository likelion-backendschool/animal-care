package com.codelion.animalcare.domain.medical_appointment.service;

import com.codelion.animalcare.domain.medical_appointment.dto.MedicalAppointmentDto;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepository;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalAppointmentQueryService {

    private final MedicalAppointmentRepository medicalAppointmentRepository;

    /**
     * Admin 페이지에서 필요할거 같음
     */
    public List<MedicalAppointmentDto> findAllMedicalAppointments() {

        List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findAllWithMemberAnimalHospitalDoctor();

        List<MedicalAppointmentDto> medicalAppointmentDtos = medicalAppointments.stream()
                .map(o -> new MedicalAppointmentDto(o))
                .collect(Collectors.toList());

        return medicalAppointmentDtos;
    }


    public List<MedicalAppointmentDto> findMedicalAppointmentByMemberId(Long memberId) {

        List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findByMemberId(memberId);

        List<MedicalAppointmentDto> medicalAppointmentDtos = medicalAppointments.stream()
                .map(o -> new MedicalAppointmentDto(o))
                .collect(Collectors.toList());

        return medicalAppointmentDtos;
    }
}
