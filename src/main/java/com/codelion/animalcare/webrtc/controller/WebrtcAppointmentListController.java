package com.codelion.animalcare.webrtc.controller;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.user.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/usr/mypage/doctor/member-manage/appointments")
@RequiredArgsConstructor
public class WebrtcAppointmentListController {

    private final DoctorService doctorService;
    private final AppointmentQueryService appointmentQueryService;

    /**
     * 비대면 진료에서 예약명단 확인
     */
    @GetMapping("/all")
    public String loadByDoctorAppointments(Model model, Principal principal) {

        LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findByEmail(principal.getName());

        List<AppointmentDto> appointmentDto = appointmentQueryService.findAllAppointment(doctorDto);

        model.addAttribute("appointmentDto", appointmentDto);

        return "appointments/appointmentByDoctorList";
    }

    // TODO 예약내역을 날짜에 따라서 분류하기
//    @GetMapping("/all2")
//    public String loadByDoctorAppointments2(@ModelAttribute("appointmentSearch") AppointmentSearch appointmentSearch, Model model, Principal principal) {
//
//        LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findByEmail(principal.getName());
//
////        List<AppointmentDto> appointmentDto = appointmentQueryService.findAllAppointment(doctorDto);
//
//        List<Appointment> appointmentDto = appointmentQueryService.findAppointments(appointmentSearch);
//        model.addAttribute("appointmentDto", appointmentDto);
//
//        return "appointments/appointmentByDoctorList";
////        return "redirect:/allSearch";
//    }
//
//
//    @GetMapping("/allSearch")
//    public String loadByDoctorAppointments(@ModelAttribute("appointmentSearch") AppointmentSearch appointmentSearch, Model model, Principal principal) {
//
//        List<Appointment> appointments = appointmentService.findAppointments(appointmentSearch);
//        model.addAttribute("appointments", appointments);
//
//        return "appointments/appointmentByDoctorList";
//    }


}
