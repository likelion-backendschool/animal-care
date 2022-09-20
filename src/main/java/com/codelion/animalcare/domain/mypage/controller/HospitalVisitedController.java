package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.mypage.dto.HospitalVisitedDto;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HospitalVisitedController {

    private final MemberService memberService;
    private final AppointmentQueryService appointmentQueryService;


    /**
     * 회원마이페이지 병원방문내역
     */
    @GetMapping("/usr/mypage/member/hospitalVisited-info")
    public String hospitalVisitedList(Model model, Principal principal) {

        Optional<MemberDto> memberDto = memberService.findByEmail(principal.getName());
        List<AppointmentDto> appointmentDto = appointmentQueryService.findAppointmentByMemberDto(memberDto.get());

        model.addAttribute("appointmentDto", appointmentDto);

        return  "myPage/member/hospitalVisited";
    }

}
