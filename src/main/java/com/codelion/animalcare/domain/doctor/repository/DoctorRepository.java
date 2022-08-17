package com.codelion.animalcare.domain.doctor.repository;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
