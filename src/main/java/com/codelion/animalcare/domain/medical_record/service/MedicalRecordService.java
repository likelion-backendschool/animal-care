package com.codelion.animalcare.domain.medical_record.service;

import com.codelion.animalcare.domain.medical_record.entity.MedicalRecord;
import com.codelion.animalcare.domain.medical_record.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> findByDoctorId(long id) {
        return medicalRecordRepository.findByDoctorId(id);
    }
}
