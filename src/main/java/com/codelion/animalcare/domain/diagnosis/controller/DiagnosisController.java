package com.codelion.animalcare.domain.diagnosis.controller;

import com.codelion.animalcare.domain.diagnosis.DiagnosisSearch;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiagnosisController {

    private final DiagnosisService diagnosisService;


    // 닥터페이지 진단서
    @GetMapping("/usr/mypage/member/diagnosis-info")
    public String showDiagnosis(@ModelAttribute("diagnosisSearch") DiagnosisSearch diagnosisSearch, Model model) {

        List<Diagnosis> diagnoses = diagnosisService.findDiagnoses(diagnosisSearch);
        model.addAttribute("diagnoses", diagnoses);

        return "diagnosis/diagnosisList";
    }

}
