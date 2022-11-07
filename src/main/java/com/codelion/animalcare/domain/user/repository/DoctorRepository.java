package com.codelion.animalcare.domain.user.repository;

import com.codelion.animalcare.domain.user.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);

    List<Doctor> findAllByHospitalId(Long hospitalId);
}
