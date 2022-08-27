package com.codelion.animalcare.domain.user.service;

import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor getDoctor(String email) {
        Doctor doctor = doctorRepository.findByEmail(email);

        return doctor;
    }
}
