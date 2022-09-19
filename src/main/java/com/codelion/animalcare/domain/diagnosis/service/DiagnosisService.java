package com.codelion.animalcare.domain.diagnosis.service;

import com.codelion.animalcare.domain.diagnosis.DiagnosisSearch;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.diagnosis.repository.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

    public List<Diagnosis> findDiagnoses(DiagnosisSearch diagnosisSearch) {
        return diagnosisRepository.findAll();
    }

    /**
     * 예약서로 진단서 찾기
     * @param appointmentId
     * @return 존재한다면 FindOneDiagnosis로 출력. 없다면 비어있는 FindOneDiagnosis로 출력.
     */
    public FindOneDiagnosis findByAppointmentId(Long appointmentId){
         Optional<Diagnosis> diagnosisOptional = diagnosisRepository.findByAppointmentId(appointmentId);
         return diagnosisOptional.map(FindOneDiagnosis::new).orElse(null);
    }
}
