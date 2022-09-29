package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.dto.AppointmentModifyDto;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member/mypage/appointment")
public class MemberMyPageAppointmentController {

    private final AppointmentQueryService appointmentQueryService;
    private final AppointmentService appointmentService;


    /**
     * 멤버마이페이지 예약내역
     */
    @GetMapping("/info")
    public String appointmentList(Model model, Principal principal) {

        String email = principal.getName();
        List<AppointmentDto> appointmentDto = appointmentQueryService.findAppointmentByEmail(email);

        model.addAttribute("appointmentDto", appointmentDto);

        return "appointments/appointmentList";
    }



    /**
     * 멤버마이페이지 예약내역 취소 CANCEL
     */
    @PostMapping("/info/{appointmentId}/cancel")
    public String cancelAppointment(@PathVariable("appointmentId") Long appointmentId) {

        appointmentService.cancelAppointment(appointmentId, AppointmentStatus.CANCEL);
        return "redirect:/usr/member/mypage/appointment/info";
    }




    /**
     * 멤버마이페이지 예약내역 시간수정 MODIFY
     */
    @GetMapping("/info/{appointmentId}/modify")
    public String updateAppointmentForm(@PathVariable("appointmentId") Long appointmentId, Model model) {

        AppointmentModifyDto appointmentModifyDto = findAppointmentModifyDto(appointmentId);
        model.addAttribute("appointmentModifyDto", appointmentModifyDto);

        return "appointments/appointmentModifyForm";
    }


    /**
     * 멤버마이페이지 예약내역 시간수정 MODIFY
     */
    @PostMapping("/info/{appointmentId}/modify")
    public String updateAppointment(
            @PathVariable("appointmentId") Long appointmentId,
            @RequestParam("inputDateId") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate) {

        appointmentService.updateAppointment(appointmentId, appointmentDate);
        return "redirect:/usr/member/mypage/appointment/info";
    }

    private AppointmentModifyDto findAppointmentModifyDto(Long appointmentId) {
        return appointmentService.findAppointmentModifyDtoById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment id " + appointmentId + " was not found."));
    }

}
