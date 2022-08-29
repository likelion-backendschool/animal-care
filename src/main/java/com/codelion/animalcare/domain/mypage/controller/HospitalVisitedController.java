package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.mypage.dto.HospitalVisitedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HospitalVisitedController {

    @GetMapping("/user/mypage/member/{memberId}/hospitalVisited-info")
    public String createForm(Model model) {
        model.addAttribute("hospitalVisitedDto", new HospitalVisitedDto());
        return "member/hospitalVisited";
    }
}
