package com.codelion.animalcare.domain.medical_appointment.service;

import com.codelion.animalcare.domain.medical_appointment.dto.MedicalAppointmentDto;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepository;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepositoryTmp;
import com.codelion.animalcare.domain.member.MemberDto;
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
    private final MedicalAppointmentRepositoryTmp medicalAppointmentRepositorytmp;

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


    public List<MemberDto> findMembers(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit){

        List<Member> members = medicalAppointmentRepositorytmp.findAll(offset, limit);

        List<MemberDto> result = members.stream()
                .map(o -> new MemberDto(o))
                .collect(Collectors.toList());

        return result;
    }


    public List<MedicalAppointmentDto> findMedicalAppointmentByMemberId(Long memberId) {

        List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findByMemberId(memberId);

        List<MedicalAppointmentDto> medicalAppointmentDtos = medicalAppointments.stream()
                .map(o -> new MedicalAppointmentDto(o))
                .collect(Collectors.toList());

        return medicalAppointmentDtos;
    }
}
