package com.codelion.animalcare.domain.user.service;

import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctormypage.dto.UpdateDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctormypage.dto.UpdateDoctorMyPageInfoPassword;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.global.error.exception.DoctorModifyAfterPasswordNotSameException;
import com.codelion.animalcare.global.error.exception.DoctorModifyBeforePasswordNotSameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public LoadDoctorMyPageInfo.ResponseDto findById(long id) {
        Doctor doctor = findDoctorById(id);

        // entity => dto
        LoadDoctorMyPageInfo.ResponseDto doctorForm = new LoadDoctorMyPageInfo.ResponseDto(doctor);

        return doctorForm;
    }

    @Transactional(readOnly = true)
    public LoadDoctorMyPageInfo.ResponseDto findByEmail(String email) {
        Doctor doctor = findDoctorByEmail(email);

        // entity => dto
        LoadDoctorMyPageInfo.ResponseDto doctorForm = new LoadDoctorMyPageInfo.ResponseDto(doctor);

        return doctorForm;
    }


    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // 의사 전체 조회
    public List<Doctor> findDoctors() {
        return doctorRepository.findAll();
    }

    // TODO 사용하는 곳 없다면 삭제
    public Doctor getDoctor(String email) {
        Doctor doctor = findDoctorByEmail(email);
        return doctor;
    }

    public void update(UpdateDoctorMyPageInfo.RequestDto doctorDto) {
        // doctor check
        Doctor beforeDoctor = findDoctorById(doctorDto.getId());

        // dto => entity
        Doctor newDoctor = doctorDto.toEntity(beforeDoctor);
        System.out.println(newDoctor);

        doctorRepository.save(newDoctor);
    }

    // TODO 비밀번호 암호화 적용.
    // 의사 비밀번호 변경
    public void updatePassword(UpdateDoctorMyPageInfoPassword.RequestDto requestDto, String email) throws DoctorModifyBeforePasswordNotSameException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // doctor check
        Doctor doctor = findDoctorByEmail(email);

        // 비밀번호 확인
        if (!encoder.matches(requestDto.getBeforePassword(), doctor.getPassword())) {
            throw new DoctorModifyBeforePasswordNotSameException("기존 비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호 체크
        if (!requestDto.getNewPassword().equals(requestDto.getNewPasswordConfirm())) {
            throw new DoctorModifyAfterPasswordNotSameException("새 비밀번호가 서로 일치하지 않습니다.");
        }



        doctor.updateLoginPwd(encoder.encode(requestDto.getNewPassword()));

        doctorRepository.save(doctor);
    }

    private Doctor findDoctorById(long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor id:" + doctorId + " can't found."));
    }

    private Doctor findDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor email:" + email + " can't found."));
    }
}