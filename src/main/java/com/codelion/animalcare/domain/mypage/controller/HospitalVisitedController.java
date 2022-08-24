package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.mypage.HospitalVisitedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HospitalVisitedController {

    @GetMapping("/usr/mypage/member/{memberId}/{hospitalVisitedId}/hospitalVisited-info")
    public String createForm(Model model) {
        model.addAttribute("hospitalVisitedDto", new HospitalVisitedDto());
        return "member/hospitalVisited";
    }
}
