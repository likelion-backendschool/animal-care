package com.codelion.animalcare.domain.doctor.service;

import com.codelion.animalcare.domain.doctor.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {


    private final DoctorRepository doctorRepository;

    public LoadDoctorMyPageInfo.ResponseDto findById(long id) {
        Doctor doctor = findDoctorById(id);

        // entity => dto
        LoadDoctorMyPageInfo.ResponseDto doctorForm = new LoadDoctorMyPageInfo.ResponseDto(doctor);

        return doctorForm;
    }

    // 의사 전체 조회
    public List<Doctor> findDoctors() {
        return doctorRepository.findAll();
    }


    public void update(UpdateDoctorMyPageInfo.RequestDto doctorDto) {
        // doctor check
        Doctor beforeDoctor = findDoctorById(doctorDto.getId());

        // dto => entity
        Doctor newDoctor = doctorDto.toEntity(beforeDoctor);

        doctorRepository.save(newDoctor);
    }


    // 의사 비밀번호 변경
    public void updatePassword(String loginPwd, long doctorId) {
        // doctor check
        Doctor doctor = findDoctorById(doctorId);

        doctor.updateLoginPwd(loginPwd);

        doctorRepository.save(doctor);
    }

    private Doctor findDoctorById(long doctorId){
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor " + doctorId + " can't found."));
    }
}
