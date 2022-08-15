package com.codelion.animalcare.domain.medical_appointment.repository;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
}
