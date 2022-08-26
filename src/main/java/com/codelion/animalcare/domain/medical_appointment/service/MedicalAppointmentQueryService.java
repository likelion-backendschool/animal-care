package com.codelion.animalcare.domain.medical_appointment.service;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepository;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepositoryTmp;
import com.codelion.animalcare.domain.member.MemberDto;
import com.codelion.animalcare.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalAppointmentQueryService {

    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final MedicalAppointmentRepositoryTmp medicalAppointmentRepositorytmp;


    public List<MedicalAppointment> findMedicalAppointments() {

        return medicalAppointmentRepository.findAllWithMemberAnimalHospitalDoctor();
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



}
