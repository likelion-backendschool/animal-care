package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member/mypage/hospitalDiagnosis/info")
public class HospitalDiagnosisController {

    private final MemberService memberService;
    private final AppointmentQueryService appointmentQueryService;


    /**
     * 회원마이페이지 병원진료내역
     */
    @GetMapping()
    public String hospitalVisitedList(Model model, Principal principal) {

        Optional<MemberDto> memberDto = memberService.findByEmail(principal.getName());
        List<AppointmentDto> appointmentDto = appointmentQueryService.findAppointmentByMemberDto(memberDto.get());

        model.addAttribute("appointmentDto", appointmentDto);

        return "myPage/member/hospitalDiagnosis";
    }

}
