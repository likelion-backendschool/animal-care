package com.codelion.animalcare.domain.medical_appointment.controller;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;

import com.codelion.animalcare.domain.medical_appointment.dto.MedicalAppointmentDto;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentQueryService;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentService;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.DoctorService;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MedicalAppointmentController {

    private final MedicalAppointmentQueryService medicalAppointmentQueryService;
    private final MedicalAppointmentService medicalAppointmentService;
    private final MemberService memberService;
    private final AnimalService animalService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;


    // 임시 예약하기
    @GetMapping("/usr/mypage/member/medical-appointment")
    public String createMedicalAppointmentForm(Model model, Principal principal) {

        Optional<Member> member = memberService.findByEmail(principal.getName());
        List<Animal> animals = animalService.findByMember(member.get());
        List<Hospital> hospitals = hospitalService.findHospitals();
        List<Doctor> doctors = doctorService.findDoctors();

        model.addAttribute("member", member.get());
        model.addAttribute("animals", animals);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("doctors", doctors);

        return "medicalAppointments/medicalAppointmentForm";
    }


    // 임시 예약하기
    @PostMapping("/usr/mypage/member/medical-appointment")
    public String medicalAppointment(
            Principal principal,
            @RequestParam("animalId") Long animalId,
            @RequestParam("hospitalId") Long hospitalId,
            @RequestParam("doctorId") Long doctorId,
            @RequestParam("inputDateId") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime medicalAppointmentDate) {

        Optional<Member> member = memberService.findByEmail(principal.getName());
        medicalAppointmentService.medicalAppointment(member.get().getId(), animalId, hospitalId, doctorId, medicalAppointmentDate);

        return "redirect:/usr/mypage/member/medical-appointment-info";
    }


    // 마이페이지 회원 예약내역
    @GetMapping("/usr/mypage/member/medical-appointment-info")
    public String medicalAppointmentListUseDto(Model model, Principal principal) {

        Optional<Member> member = memberService.findByEmail(principal.getName());
        List<MedicalAppointmentDto> medicalAppointmentDtos = medicalAppointmentQueryService.findMedicalAppointmentByMemberId(member.get().getId());

        model.addAttribute("medicalAppointmentDtos", medicalAppointmentDtos);

        return "medicalAppointments/medicalAppointmentList";
    }

    // 모든 예약 내역
//    @GetMapping("/usr/mypage/member/medical-appointment-info")
//    public String medicalAppointmentListUseDto(Model model) {
//
//        List<MedicalAppointmentDto> medicalAppointmentDtos = medicalAppointmentQueryService.findAllMedicalAppointments();
//
//        model.addAttribute("medicalAppointmentDtos", medicalAppointmentDtos);
//
//        return "medicalAppointments/medicalAppointmentList";
//    }


    // 마이페이지 회원 예약정보 취소
    @PostMapping("/usr/mypage/member/medical-appointment-info/{medicalAppointmentId}/cancel")
    public String cancelMedicalAppointment(@PathVariable("medicalAppointmentId") Long medicalAppointmentId) {
        medicalAppointmentService.cancelMedicalAppointment(medicalAppointmentId);
        return "redirect:/usr/mypage/member/medical-appointment-info";
    }


    // TODO 마이페이지 회원 예약정보 수정
//    @GetMapping("/usr/mypage/member/{memberId}/medical-appointment-info/{medicalAppointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable("medicalAppointmentId") Long medicalAppointmentId, Model model) {
//
//        return "redirect:/usr/mypage/member/{memberId}/medical-appointment";
//    }

//    @PostMapping("/usr/mypage/member/{memberId}/medical-appointment-info/{medicalAppointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable Long medicalAppointmentId) {
//
//        medicalAppointmentService.updateMedicalAppointment(medicalAppointmentId);
//
//        return "redirect:/usr/mypage/member/{memberId}/medical-appointment";
//    }
}
