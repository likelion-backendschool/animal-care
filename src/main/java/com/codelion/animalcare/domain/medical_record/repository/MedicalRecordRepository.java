package com.codelion.animalcare.domain.medical_record.repository;

import com.codelion.animalcare.domain.medical_record.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
