package com.codelion.animalcare.web.mypage.controller;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.service.DoctorService;
import com.codelion.animalcare.web.mypage.dto.UpdateDoctorMyPageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usr/my-page/doctor")
@RequiredArgsConstructor
public class DoctorMyPageController {
    private final DoctorService doctorService;


    @GetMapping()
    public String loadDoctorMyPage(){
        return "myPage/doctor/index";
    }

    @GetMapping("{doctorId}/hospital-info-manage")
    public String loadDoctorMyPageHospitalInfoManage(Model model){

        return "myPage/doctor/hospital-info-manage";
    }

    @GetMapping("{doctorId}/patient-manage")
    public String loadDoctorMyPagePatientManage(){

        return "myPage/doctor/patient-manage";
    }



    // 내 정보
    @GetMapping("{doctorId}/info")
    public String loadDoctorMyPageInfo(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        model.addAttribute("doctor",doctor);

        return "myPage/doctor/info";
    }

    // 내 정보 수정 페이지
    @GetMapping("{doctorId}/info/modify")
    public String loadDoctorMyPageInfoModify(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        model.addAttribute("doctor", doctor);

        return "myPage/doctor/info-modify";
    }

    // 내 정보 수정 요청
    @PostMapping("{doctorId}/info/doModify")
    public String updateDoctorMyPageInfo(
            Model model, @PathVariable long doctorId, @RequestBody  UpdateDoctorMyPageInfo.RequestDto body
    ){
        // 존재 하는지 체크
        Doctor beforeDoctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // dto => entity
        Doctor afterDoctor = body.toEntity(beforeDoctor);
        doctorService.save(afterDoctor);

        return "redirect:/usr/my-page/doctor/{doctorId}/info";
    }
}
