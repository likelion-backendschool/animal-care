package com.codelion.animalcare.domain.appointment.controller;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import com.codelion.animalcare.domain.mypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.hospital.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.service.HospitalService;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.DoctorService;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member/mypage/appointment")
public class AppointmentController {

    private final AppointmentQueryService appointmentQueryService;
    private final AppointmentService appointmentService;
    private final MemberService memberService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;


    // 임시 예약하기
    @GetMapping()
    public String createAppointmentForm(Model model, Principal principal) {

        MemberDto memberDto = findMemberDto(principal);
        List<AnimalDto> animalDtos = memberDto.getAnimals();
        List<LoadDoctorMyPageHospitalInfoManage.ResponseDto> hospitalDtos = hospitalService.findHospitals();
        List<LoadDoctorMyPageInfo.ResponseDto> doctorDtos = doctorService.findDoctors();

        model.addAttribute("memberDto", memberDto);
        model.addAttribute("animalDtos", animalDtos);
        model.addAttribute("hospitalDtos", hospitalDtos);
        model.addAttribute("doctorDtos", doctorDtos);

        return "appointments/appointmentForm";
    }


    // 임시 예약하기
    @PostMapping()
    public String appointment(
            Principal principal,
            @RequestParam("animalDtosId") Long animalDtosId,
            @RequestParam("hospitalDtosId") Long hospitalDtosId,
            @RequestParam("doctorDtosId") Long doctorDtosId,
            @RequestParam("inputDateId") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate) {

        MemberDto memberDto = findMemberDto(principal);
        appointmentService.appointment(memberDto, animalDtosId, hospitalDtosId, doctorDtosId, appointmentDate);

        return "redirect:/usr/member/mypage/appointment/info";
    }

    /**
     * 회원마이페이지 예약내역
     */
    @GetMapping("/info")
    public String appointmentList(Model model, Principal principal) {

        String email = principal.getName();
        List<AppointmentDto> appointmentDto = appointmentQueryService.findAppointmentByEmail(email);

        model.addAttribute("appointmentDto", appointmentDto);

        return "appointments/appointmentList";
    }


    /**
     * 회원마이페이지 예약내역 취소 CANCEL
     */
    // 마이페이지 회원 예약정보 취소
    @PostMapping("/info/{appointmentId}/cancel")
    public String cancelAppointment(@PathVariable("appointmentId") Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return "redirect:/usr/member/mypage/appointment/info";
    }


    /**
     * 회원마이페이지 예약내역 수정 MODIFY
     */
    @GetMapping("/info/{appointmentId}/modify")
    public String updateAppointmentForm(@PathVariable("appointmentId") Long appointmentId,
                                        Model model,
                                        Principal principal) {

        MemberDto memberDto = findMemberDto(principal);
        List<AnimalDto> animalDtos = memberDto.getAnimals();
        List<LoadDoctorMyPageHospitalInfoManage.ResponseDto> hospitalDtos = hospitalService.findHospitals();
        List<LoadDoctorMyPageInfo.ResponseDto> doctorDtos = doctorService.findDoctors();

        AppointmentDto appointmentDto = findAppointmentDto(appointmentId);

        model.addAttribute("memberDto", memberDto);
        model.addAttribute("animalDtos", animalDtos);
        model.addAttribute("hospitalDtos", hospitalDtos);
        model.addAttribute("doctorDtos", doctorDtos);
        model.addAttribute("appointmentDto", appointmentDto);

        return "appointments/appointmentModifyForm";
    }


    /**
     * 회원마이페이지 수정 MODIFY
     */
    @PostMapping("/info/{appointmentId}/modify")
    public String updateAppointment(
            Principal principal,
            @PathVariable("appointmentId") Long appointmentId,
            @RequestParam("animalDtosId") Long animalDtosId,
            @RequestParam("hospitalDtosId") Long hospitalDtosId,
            @RequestParam("doctorDtosId") Long doctorDtosId,
            @RequestParam("inputDateId") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate) {

        MemberDto memberDto = findMemberDto(principal);
        appointmentService.updateAppointment(appointmentId, memberDto, animalDtosId, hospitalDtosId, doctorDtosId, appointmentDate);

        return "redirect:/usr/member/mypage/appointment/info";
    }


    private MemberDto findMemberDto(Principal principal) {
        String email = principal.getName();
        return memberService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("member email " + email + " was not found."));
    }

    private AppointmentDto findAppointmentDto(Long appointmentId) {
        return appointmentService.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment id " + appointmentId + " was not found."));
    }
}
