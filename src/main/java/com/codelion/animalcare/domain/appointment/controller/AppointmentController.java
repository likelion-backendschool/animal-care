package com.codelion.animalcare.domain.appointment.controller;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.user.entity.Doctor;
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
public class AppointmentController {

    private final AppointmentQueryService appointmentQueryService;
    private final AppointmentService appointmentService;
    private final MemberService memberService;
    private final AnimalService animalService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;


    // 임시 예약하기
    @GetMapping("/usr/mypage/member/appointment")
    public String createAppointmentForm(Model model, Principal principal) {

        Optional<Member> member = memberService.findByEmail(principal.getName());
        List<Animal> animals = animalService.findByMember(member.get());
        List<Hospital> hospitals = hospitalService.findHospitals();
        List<Doctor> doctors = doctorService.findDoctors();

        model.addAttribute("member", member.get());
        model.addAttribute("animals", animals);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("doctors", doctors);

        return "appointments/appointmentForm";
    }


    // 임시 예약하기
    @PostMapping("/usr/mypage/member/appointment")
    public String appointment(
            Principal principal,
            @RequestParam("animalId") Long animalId,
            @RequestParam("hospitalId") Long hospitalId,
            @RequestParam("doctorId") Long doctorId,
            @RequestParam("inputDateId") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate) {

        Optional<Member> member = memberService.findByEmail(principal.getName());
        appointmentService.appointment(member.get().getId(), animalId, hospitalId, doctorId, appointmentDate);

        return "redirect:/usr/mypage/member/appointment-info";
    }

    @GetMapping("/usr/member/appointment")
    public String appointment(Model model){
        return "appointments/appointmentHospitalMap";
    }

    // 마이페이지 회원 예약내역
    @GetMapping("/usr/mypage/member/appointment-info")
    public String appointmentListUseDto(Model model, Principal principal) {

        Optional<Member> member = memberService.findByEmail(principal.getName());
        List<AppointmentDto> appointmentDtos = appointmentQueryService.findAppointmentByMemberId(member.get().getId());

        model.addAttribute("appointmentDtos", appointmentDtos);

        return "appointments/appointmentList";
    }

    // 모든 예약 내역
//    @GetMapping("/usr/mypage/member/appointment-info")
//    public String appointmentListUseDto(Model model) {
//
//        List<AppointmentDto> appointmentDtos = appointmentQueryService.findAllAppointments();
//
//        model.addAttribute("appointmentDtos", appointmentDtos);
//
//        return "appointments/appointmentList";
//    }


    // 마이페이지 회원 예약정보 취소
    @PostMapping("/usr/mypage/member/appointment-info/{appointmentId}/cancel")
    public String cancelAppointment(@PathVariable("appointmentId") Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return "redirect:/usr/mypage/member/appointment-info";
    }


//    // TODO 마이페이지 회원 예약정보 수정
//    @GetMapping("/usr/mypage/member/appointment-info/{appointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable("appointmentId") Long appointmentId, Model model) {
//
//        return "redirect:/usr/mypage/member/appointment";
//    }
//
//    @PostMapping("/usr/mypage/member/appointment-info/{appointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable Long appointmentId) {
//
//        appointmentService.updateAppointment(appointmentId);
//        return "redirect:/usr/mypage/member/appointment";
//    }
}
