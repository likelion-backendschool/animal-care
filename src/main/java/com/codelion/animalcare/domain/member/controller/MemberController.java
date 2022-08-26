package com.codelion.animalcare.domain.member.controller;


import com.codelion.animalcare.domain.member.MemberForm;
import com.codelion.animalcare.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/usr/mypage/member/info")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/info";
    }


}
