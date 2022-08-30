package com.codelion.animalcare.domain.mypage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class MyPageHomeController {


    @GetMapping("/user/mypage/member/{memberId}")
    public String myPageHome() {
        log.info("myPageHome controller");

        return "myPage";
    }

}