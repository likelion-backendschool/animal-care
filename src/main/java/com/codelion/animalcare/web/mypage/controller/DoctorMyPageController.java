package com.codelion.animalcare.web.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usr/my-page/doctor")
public class DoctorMyPageController {

    @GetMapping()
    public String loadDoctorMyPage(){
        return "myPage/doctor/index";
    }

    @GetMapping("/{doctorId}/hospital-info-manage")
    public String loadDoctorMyPageHospitalInfoManage(){
        return "myPage/doctor/hospital-info-manage";
    }

    @GetMapping("/{doctorId}/patient-manage")
    public String loadDoctorMyPagePatientManage(){
        return "myPage/doctor/patient-manage";
    }

    @GetMapping("/{doctorId}/info")
    public String loadDoctorMyPageInfo(){
        return "myPage/doctor/info";
    }
}
