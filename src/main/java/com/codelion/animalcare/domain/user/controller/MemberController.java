package com.codelion.animalcare.domain.user.controller;

import com.codelion.animalcare.domain.user.entity.MemberForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 회원 마이페이지 내정보
 */
@Controller
public class MemberController {

    @GetMapping("/usr/mypage/member/info")
    public String myInfo(Model model) {

        model.addAttribute("MemberForm", new MemberForm());

        return "member/info";
    }
}
