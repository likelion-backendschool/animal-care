package com.codelion.animalcare.domain.diagnosis.repository;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {


}
