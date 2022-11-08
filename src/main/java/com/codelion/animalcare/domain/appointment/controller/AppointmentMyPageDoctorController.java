package com.codelion.animalcare.domain.appointment.controller;

import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;

import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;

import com.codelion.animalcare.domain.appointment.service.AppointmentCommandService;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("usr/doctor/mypage/appointments")
@RequiredArgsConstructor
public class AppointmentMyPageDoctorController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;
    private final DiagnosisService diagnosisService;

    // TODO page 설정
    /**
     * 환자 예약 관리
     */
    @GetMapping()
    public String loadMyPageDoctorAppointments(
            Model model,
            Principal principal,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        String email = principal.getName();
        List<LoadMyPageDoctorAppointment.ResponseDto> appointments
                = appointmentQueryService.findAllByDoctorEmail(email);
        model.addAttribute("appointments", appointments);
        return "myPage/doctor/member-manage";
    }

    /**
     * 예약 환자 거절 기능
     */
    @PostMapping("{appointmentId}/refuse")
    public String refuseAppointment(
            @PathVariable long appointmentId
    ){
        appointmentCommandService.updateAppointmentStatus(appointmentId, AppointmentStatus.REFUSE);

        return String.format("redirect:/usr/doctor/mypage/appointments/%d",appointmentId);
    }

    /**
     * 환자 정보 확인
     */
    @GetMapping("{appointmentId}")
    public String loadMyPageDoctorAppointment(
            Model model,
            @PathVariable long appointmentId
    ){
        LoadMyPageDoctorAppointment.ResponseDto appointment
                = appointmentQueryService.findById(appointmentId);

        FindOneDiagnosis diagnosis = diagnosisService.findByAppointmentId(appointment.getId());


        model.addAttribute("appointment", appointment);
        model.addAttribute("member", appointment.getMember());
        model.addAttribute("animal", appointment.getAnimal());
        model.addAttribute("hospital", appointment.getHospital());
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("doctor", appointment.getDoctor());
        return "myPage/doctor/member-manage-self";
    }
}
