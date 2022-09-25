package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/usr/member/mypage")
@RequiredArgsConstructor
public class MemberMyPageInfoController {
    private final MemberService memberService;

    @GetMapping("")
    public String myPageHome() {

        return "myPage/member/myPageForm";
    }
    @GetMapping("/info")
    public String loadMemberMyPageInfo(Model model, Principal principal){
        String email = principal.getName();
        MemberDto memberDto =  memberService.findByEmail(email).get();
        model.addAttribute("memberDto", memberDto);
        return "myPage/member/info";
    }
}
