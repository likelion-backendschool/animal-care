package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usr/member/mypage")
@RequiredArgsConstructor
public class MemberMyPageInfoController {
    private final MemberService memberService;

    @GetMapping("")
    public String myPageHome() {

        return "myPage/member/myPageForm";
    }
}
