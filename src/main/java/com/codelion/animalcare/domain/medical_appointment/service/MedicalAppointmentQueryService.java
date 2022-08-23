package com.codelion.animalcare.domain.medical_appointment.service;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalAppointmentQueryService {

    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public List<MedicalAppointment> findMedicalAppointments() {

        return medicalAppointmentRepository.findAllWithMemberAnimalHospitalDoctor();
    }
}
