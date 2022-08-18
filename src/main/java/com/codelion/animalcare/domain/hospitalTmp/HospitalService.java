package com.codelion.animalcare.domain.hospitalTmp;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 임시 만듦
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Hospital hospital) {

        validateDuplicateHospital(hospital); //중복 병원 검증
        hospitalRepository.save(hospital);
        return hospital.getId();
    }

    private void validateDuplicateHospital(Hospital hospital) {
        List<Hospital> findHospitals = hospitalRepository.findByName(hospital.getName());
        if (!findHospitals.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 병원입니다.");
        }
    }

    //병원 전체 조회
    public List<Hospital> findHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital findOne(Long hospitalId) {
        return hospitalRepository.findOne(hospitalId);
    }


}
