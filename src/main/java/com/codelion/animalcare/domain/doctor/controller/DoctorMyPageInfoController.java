package com.codelion.animalcare.domain.doctor.controller;

import com.codelion.animalcare.domain.doctor.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usr/mypage/doctor/{doctorId}/info")
@RequiredArgsConstructor
public class DoctorMyPageInfoController {
    private final DoctorService doctorService;

    // 내 정보
    @GetMapping()
    public String loadDoctorMyPageInfo(Model model, @PathVariable long doctorId){
        LoadDoctorMyPageInfo.ResponseDto doctorForm = doctorService.findById(doctorId);

        model.addAttribute("doctor", doctorForm);

        return "myPage/doctor/info";
    }

    // 내 정보 수정 페이지
    @GetMapping("modify")
    public String loadDoctorMyPageInfoModify(Model model, @PathVariable long doctorId){
        LoadDoctorMyPageInfo.ResponseDto doctorForm = doctorService.findById(doctorId);

        model.addAttribute("doctor", doctorForm);

        return "myPage/doctor/info-modify";
    }


    // 내 정보 수정 요청(비밀번호 제외)
    @PostMapping("modify")
    public String updateDoctorMyPageInfo(
            UpdateDoctorMyPageInfo.RequestDto doctorDto,
            @PathVariable long doctorId
    ){
        doctorDto.setId(doctorId);

        doctorService.update(doctorDto);

        return "redirect:/usr/mypage/doctor/{doctorId}/info";
    }

    // 비밀번호 수정.
    @PostMapping("modify/password")
    public String updateDoctorMyPageInfoPassword(
            String loginPwd,
            @PathVariable long doctorId
    ){
        doctorService.updatePassword(loginPwd, doctorId);

        return "redirect:/usr/mypage/doctor/{doctorId}/info";
    }
}
