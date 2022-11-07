package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member/mypage/hospitalDiagnosis/info")
public class HospitalDiagnosisController {

    private final AppointmentQueryService appointmentQueryService;


    /**
     * 회원마이페이지 병원진료내역
     */
    @GetMapping()
    public String hospitalDiagnosisList(Model model, Principal principal) {

        String email = principal.getName();
        List<AppointmentDto> appointmentDto = appointmentQueryService.findAppointmentByEmail(email);

        model.addAttribute("appointmentDto", appointmentDto);

        return "myPage/member/hospitalDiagnosis";
    }

}
