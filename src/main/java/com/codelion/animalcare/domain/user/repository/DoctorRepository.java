package com.codelion.animalcare.domain.user.repository;

import com.codelion.animalcare.domain.user.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByEmail(String email);
}
