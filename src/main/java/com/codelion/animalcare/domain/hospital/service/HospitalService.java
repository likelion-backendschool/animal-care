package com.codelion.animalcare.domain.hospital.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.domain.hospital.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.dto.UpdateDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    @Transactional(readOnly = true)
    public Optional<Hospital> findById(long id) {
        return hospitalRepository.findById(id);
    }

    //병원 전체 조회
    @Transactional(readOnly = true)
    public List<Hospital> findHospitals() {
        return hospitalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LoadDoctorMyPageHospitalInfoManage.ResponseDto findByDoctorId(long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor " + doctorId + " can't found."));

        // doctor에서 hospital 추출
        Hospital hospital = doctor.getHospital();

        // entity => dto
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalForm =
                new LoadDoctorMyPageHospitalInfoManage.ResponseDto(hospital);

        return hospitalForm;
    }


    public void update(UpdateDoctorMyPageHospitalInfoManage.RequestDto hospitalDto) {
        Hospital beforeHospital =  hospitalRepository.findById(hospitalDto.getId())
                .orElseThrow(() -> new RuntimeException("Hospital " + hospitalDto.getId() + " can't found."));

        // entity => dto
        Hospital afterHospital = hospitalDto.toEntity(beforeHospital);

        hospitalRepository.save(afterHospital);
    }
}
