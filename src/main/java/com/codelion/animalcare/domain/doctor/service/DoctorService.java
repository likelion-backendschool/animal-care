package com.codelion.animalcare.domain.doctor.service;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {


    private final DoctorRepository doctorRepository;

    public Optional<Doctor> findById(long id) {
        return doctorRepository.findById(id);
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    //의사 전체 조회
    public List<Doctor> findDoctors() {
        return doctorRepository.findAll();
    }

}
