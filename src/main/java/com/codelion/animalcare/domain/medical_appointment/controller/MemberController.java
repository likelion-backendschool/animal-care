package com.codelion.animalcare.domain.medical_appointment.controller;

import com.codelion.animalcare.domain.member.MemberForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 회원 마이페이지 내정보
 */
@Controller
public class MemberController {

    @GetMapping("/user/mypage/member/{memberId}/info")
    public String myInfo(Model model) {

        model.addAttribute("MemberForm", new MemberForm());

        return "member/info";
    }
}
