package com.codelion.animalcare.domain.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/usr/mypage/member/info")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/info";
    }
}
