package com.codelion.animalcare.domain.user.repository;

import com.codelion.animalcare.domain.user.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    Patient findByEmail(String email);
}
