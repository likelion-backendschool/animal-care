package com.codelion.animalcare.domain.member.controller;


import com.codelion.animalcare.domain.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/usr/mypage/member/info")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberDto());
        return "member/info";
    }
}
