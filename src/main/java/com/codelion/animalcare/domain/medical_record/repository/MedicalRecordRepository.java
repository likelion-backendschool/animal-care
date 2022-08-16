package com.codelion.animalcare.domain.medical_record.repository;

import com.codelion.animalcare.domain.medical_record.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByDoctorId(long id);
}
