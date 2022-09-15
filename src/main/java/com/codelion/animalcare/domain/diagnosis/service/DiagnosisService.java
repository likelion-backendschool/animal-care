package com.codelion.animalcare.domain.diagnosis.service;

import com.codelion.animalcare.domain.diagnosis.DiagnosisSearch;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.diagnosis.repository.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    public List<Diagnosis> findDiagnoses(DiagnosisSearch diagnosisSearch) {
        return diagnosisRepository.findAll();
    }

}
