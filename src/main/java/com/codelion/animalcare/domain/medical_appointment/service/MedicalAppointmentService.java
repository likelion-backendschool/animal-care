package com.codelion.animalcare.domain.medical_appointment.service;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalAppointmentService {
    private final MedicalAppointmentRepository medicalAppointmentRepository;

    public List<MedicalAppointment> findByDoctorId(long id) {
        return medicalAppointmentRepository.findByDoctorId(id);
    }
}
