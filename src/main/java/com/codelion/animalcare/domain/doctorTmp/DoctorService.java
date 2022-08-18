package com.codelion.animalcare.domain.doctorTmp;

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
public class DoctorService {

    private final DoctorRepository doctorRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Doctor doctor) {

        validateDuplicateDoctor(doctor); //중복 의사 검증
        doctorRepository.save(doctor);
        return doctor.getId();
    }

    private void validateDuplicateDoctor(Doctor doctor) {
        List<Doctor> findDoctors = doctorRepository.findByName(doctor.getName());
        if (!findDoctors.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 의사입니다.");
        }
    }

    //의사 전체 조회
    public List<Doctor> findDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findOne(Long doctorId) {
        return doctorRepository.findOne(doctorId);
    }

}
