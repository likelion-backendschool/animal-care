package com.codelion.animalcare.domain.diagnosis.controller;

import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/usr/mypage/doctor/{doctorId}/member-manage/diagnosis")
@RequiredArgsConstructor
public class DiagnosisMypageDoctorController {
    private final DiagnosisService diagnosisService;
    // 환자 진료서 관리
    @GetMapping()
    public String loadMyPageDoctorDiagnosis(
            Model model,
            @PathVariable long doctorId,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){

        List<Diagnosis> diagnoses = diagnosisService.findByDoctorId(doctorId);
        // TODO dto 사용
        model.addAttribute("diagnoses",diagnoses);

        return "myPage/doctor/patient-manage/medical-records";
    }
}