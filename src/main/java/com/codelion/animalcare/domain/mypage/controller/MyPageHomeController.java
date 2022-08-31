package com.codelion.animalcare.domain.mypage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class MyPageHomeController {


    @GetMapping("/usr/mypage/member")
    public String myPageHome() {
        log.info("myPageHome controller");

        return "myPage";
    }

    @GetMapping("/usr/mypage/doctor")
    public String loadDoctorMyPage(){
        return "myPage/doctor/index";
    }

}