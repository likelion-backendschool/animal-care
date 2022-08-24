package com.codelion.animalcare.domain.doctor.service;

import com.codelion.animalcare.domain.doctor.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageInfoPassword;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.repository.DoctorRepository;
import com.codelion.animalcare.global.error.exception.DoctorModifyAfterPasswordNotSameException;
import com.codelion.animalcare.global.error.exception.DoctorModifyBeforePasswordNotSameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
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
        System.out.println(newDoctor);

        doctorRepository.save(newDoctor);
    }


    // 의사 비밀번호 변경
    public void updatePassword(UpdateDoctorMyPageInfoPassword.RequestDto requestDto, Long doctorId) throws DoctorModifyBeforePasswordNotSameException {
        // doctor check
        Doctor doctor = findDoctorById(doctorId);


        // TODO 비밀번호 검사 쪽 디미터 법칙 적용
        // 비밀번호 확인
        if(!doctor.getLoginPwd().equals(requestDto.getBeforePassword())){
            throw new DoctorModifyBeforePasswordNotSameException("기존 비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호 체크
        if(!requestDto.getNewPassword().equals(requestDto.getNewPasswordConfirm())){
            throw new DoctorModifyAfterPasswordNotSameException("새 비밀번호가 서로 일치하지 않습니다.");
        }

        doctor.updateLoginPwd(requestDto.getNewPassword());

        doctorRepository.save(doctor);
    }

    private Doctor findDoctorById(long doctorId){
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor " + doctorId + " can't found."));
    }
}
