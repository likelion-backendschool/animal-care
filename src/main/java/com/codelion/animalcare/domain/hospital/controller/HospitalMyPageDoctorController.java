package com.codelion.animalcare.domain.hospital.controller;

import com.codelion.animalcare.domain.hospital.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.dto.UpdateDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/usr/mypage/doctor/hospital-info-manage")
@RequiredArgsConstructor
public class HospitalMyPageDoctorController {
    private final HospitalService hospitalService;

    // 병원 소개
    @GetMapping()
    public String loadDoctorMyPageHospitalInfoManage(
            Model model,
            Principal principal
    ){
        String doctorEmail = principal.getName();
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalDto = hospitalService.findByDoctorEmail(doctorEmail);

        model.addAttribute("hospital", hospitalDto);
        model.addAttribute("OpeningHours", hospitalDto.makeOpeningHoursToObject());

        System.out.println(hospitalDto.getDoctorList());
        return "myPage/doctor/hospital-info-manage";
    }

    // 병원 소개 수정 페이지
    @GetMapping("modify")
    public String loadDoctorMyPageHospitalInfoManageModify(
            Model model,
            Principal principal
    ){
        String doctorEmail = principal.getName();
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalDto = hospitalService.findByDoctorEmail(doctorEmail);

        model.addAttribute("requestDto", hospitalDto);

        return "myPage/doctor/hospital-info-manage-modify";
    }

    // 병원 소개 수정 요청
    @PostMapping("modify")
    public String updateDoctorMyPageHospitalInfoManage(
            Model model,
            @Valid UpdateDoctorMyPageHospitalInfoManage.RequestDto requestDto,
            BindingResult bindingResult
        ){
            if(bindingResult.hasErrors()){
                return "myPage/doctor/hospital-info-manage-modify";
            }

            hospitalService.update(requestDto);

        return "redirect:/usr/mypage/doctor/hospital-info-manage";
    }
}
