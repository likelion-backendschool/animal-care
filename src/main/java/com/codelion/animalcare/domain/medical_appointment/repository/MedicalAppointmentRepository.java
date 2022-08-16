package com.codelion.animalcare.domain.medical_appointment.repository;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    List<MedicalAppointment> findByDoctorId(long id);
}
