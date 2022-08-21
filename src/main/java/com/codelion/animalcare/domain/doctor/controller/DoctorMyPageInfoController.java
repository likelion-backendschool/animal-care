package com.codelion.animalcare.domain.doctor.controller;

import com.codelion.animalcare.domain.doctor.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
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
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // entity => dto
        LoadDoctorMyPageInfo.ResponseDto doctorForm = new LoadDoctorMyPageInfo.ResponseDto(doctor);

        model.addAttribute("doctor",doctorForm);

        return "myPage/doctor/info";
    }

    // 내 정보 수정 페이지
    @GetMapping("modify")
    public String loadDoctorMyPageInfoModify(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // entity => dto
        LoadDoctorMyPageInfo.ResponseDto doctorForm = new LoadDoctorMyPageInfo.ResponseDto(doctor);

        model.addAttribute("doctor", doctorForm);

        return "myPage/doctor/info-modify";
    }


    // 내 정보 수정 요청(비밀번호 제외)
    @PostMapping("modify")
    public String updateDoctorMyPageInfo(
            UpdateDoctorMyPageInfo.RequestDto body,
            @PathVariable long doctorId,
            Model model
    ){
        // 존재 하는지 체크
        Doctor beforeDoctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // dto => entity
        Doctor afterDoctor = body.toEntity(beforeDoctor);

        doctorService.save(afterDoctor);

        return "redirect:/usr/my-page/doctor/{doctorId}/info";
    }

    // 비밀번호 수정.
    @PostMapping("{doctorId}/info/modify/password")
    public void updateDoctorMyPageInfoPassword(
            String loginPwd,
            @PathVariable long doctorId,
            Model model
    ){
        // doctor check
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        doctor.updateLoginPwd(loginPwd);

        doctorService.save(doctor);
    }

}
