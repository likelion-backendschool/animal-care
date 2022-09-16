package com.codelion.animalcare.domain.appointment.controller;

import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member/appointment")
public class AppointmentMemberController {
    private final MemberService memberService;

    @GetMapping()
    public String appointment(Model model, Principal principal){
        String email = principal.getName();
        MemberDto memberDto = memberService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("member email " + email + " was not found."));

        model.addAttribute("memberAddress", memberDto.getAddress());

        return "appointments/appointmentHospitalMap";
    }

}
