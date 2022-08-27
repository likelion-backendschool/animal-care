package com.codelion.animalcare.domain.user.service;

import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Optional<Doctor> findById(long id) {
        return doctorRepository.findById(id);
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findDoctors() {
        return doctorRepository.findAll();
    public Doctor getDoctor(String email) {
        Doctor doctor = doctorRepository.findByEmail(email);

        return doctor;
    }
}
