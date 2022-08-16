package com.codelion.animalcare.domain.medical_record.service;

import com.codelion.animalcare.domain.medical_record.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
}
