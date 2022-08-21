package com.codelion.animalcare.domain.hospital.controller;

import com.codelion.animalcare.domain.hospital.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.dto.UpdateDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.service.DoctorService;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usr/mypage/doctor/{doctorId}/hospital-info-manage")
@RequiredArgsConstructor
public class HospitalMyPageDoctorController {
    private final DoctorService doctorService;
    private final HospitalService hospitalService;

    // 병원 소개
    @GetMapping()
    public String loadDoctorMyPageHospitalInfoManage(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        Hospital hospital = doctor.getHospital();

        // Entity => dto
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalForm = new LoadDoctorMyPageHospitalInfoManage.ResponseDto(hospital);

        model.addAttribute("hospitalForm", hospitalForm);

        return "myPage/doctor/hospital-info-manage";
    }

    // 병원 소개 수정 페이지
    @GetMapping("modify")
    public String loadDoctorMyPageHospitalInfoManageModify(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        Hospital hospital = doctor.getHospital();

        // Entity => dto
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalForm = new LoadDoctorMyPageHospitalInfoManage.ResponseDto(hospital);

        model.addAttribute("hospitalForm", hospitalForm);

        return "myPage/doctor/hospital-info-manage-modify";
    }

    // 병원 소개 수정 요청
    @PostMapping("modify")
    public String updateDoctorMyPageHospitalInfoManage(
            Model model,
            @PathVariable long doctorId,
            @RequestBody UpdateDoctorMyPageHospitalInfoManage.RequestDto body
    ){
        // doctor check
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // hospital check
        Hospital beforeHospital = doctor.getHospital();

        if(beforeHospital == null) throw new RuntimeException(doctorId + "don't have hospital information.");

        // DTO => Entity
        Hospital afterHospital = body.toEntity(beforeHospital);

        hospitalService.save(afterHospital);

        return "redirect:/usr/my-page/doctor/{doctorId}/hospital-info-manage";
    }
}
