package com.codelion.animalcare.domain.hospital.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.repository.DoctorRepository;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalService {


    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    public Optional<Hospital> findById(long id) {
        return hospitalRepository.findById(id);
    }

    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    //병원 전체 조회
    public List<Hospital> findHospitals() {
        return hospitalRepository.findAll();
    }

    //병원에 따른 닥터 조회
//    public List<Doctor> findDoctors(long hospitalId) {
//
//        return doctorRepository.findAllById(hospitalId);
//    }

}
