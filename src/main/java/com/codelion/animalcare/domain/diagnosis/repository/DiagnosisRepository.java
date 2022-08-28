package com.codelion.animalcare.domain.diagnosis.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    List<Diagnosis> findByDoctorId(long id);
}
