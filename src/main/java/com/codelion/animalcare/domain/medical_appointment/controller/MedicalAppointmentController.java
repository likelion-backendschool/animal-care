package com.codelion.animalcare.domain.medical_appointment.controller;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import com.codelion.animalcare.domain.medical_appointment.MedicalAppointmentStatus;

import com.codelion.animalcare.domain.medical_appointment.dto.MedicalAppointmentDto;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentQueryService;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentService;
import com.codelion.animalcare.domain.member.MemberDto;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.DoctorService;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MedicalAppointmentController {

    private final MedicalAppointmentQueryService medicalAppointmentQueryService;
    private final MedicalAppointmentService medicalAppointmentService;
    private final MemberService memberService;
    private final AnimalService animalService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;


    // 예약하기 임시 만듦1
    @GetMapping("/usr/mypage/member/{memberId}/medical-appointment")
    public String createMedicalAppointmentForm(Model model) {

//        TODO memberId에 맞추어 구현해야함
//        List<Animal> animals = animalService.findAnimals();
//        List<Hospital> hospitals = hospitalService.findHospitals();
//        List<Doctor> doctors = doctorService.findDoctors();
//
//        model.addAttribute("animals", animals);
//        model.addAttribute("hospitals", hospitals);
//        model.addAttribute("doctors", doctors);

        return "medicalAppointments/medicalAppointmentForm";
    }



    // 예약하기 임시 만듦2
    @PostMapping("/usr/mypage/member/{memberId}/medical-appointment")
    public String medicalAppointment(
            @RequestParam("memberId") Long memberId,
            @RequestParam("animalId") Long animalId,
            @RequestParam("hospitalId") Long hospitalId,
            @RequestParam("doctorId") Long doctorId,
            @RequestParam("inputDateId") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime medicalAppointmentDate)
                                      {

        medicalAppointmentService.medicalAppointment(memberId, animalId, hospitalId, doctorId, medicalAppointmentDate);
        return "redirect:/usr/mypage/member/{memberId}/medical-appointment-info";
    }


    // 마이페이지 회원 예약내역
    @GetMapping("/usr/mypage/member/{memberId}/medical-appointment-info")
    public String medicalAppointmentListUseDto(Model model) {

        List<MedicalAppointmentDto> medicalAppointmentDtos = medicalAppointmentQueryService.findMedicalAppointments();

        model.addAttribute("medicalAppointmentDtos", medicalAppointmentDtos);

        return "medicalAppointments/medicalAppointmentList";
    }



    // 마이페이지 회원 예약정보 취소
    @PostMapping("/usr/mypage/member/{memberId}/medical-appointment-info/{medicalAppointmentId}/cancel")
    public String cancelMedicalAppointment(@PathVariable("medicalAppointmentId") Long medicalAppointmentId) {
        medicalAppointmentService.cancelMedicalAppointment(medicalAppointmentId);
        return "redirect:/usr/mypage/member/{memberId}/medical-appointment-info";
    }


    // 마이페이지 회원 예약정보 수정
//    @GetMapping("/usr/mypage/member/{memberId}/medical-appointment/{medicalAppointmentId}/medical-appointment-info/{medicalAppointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable("medicalAppointmentId") Long medicalAppointmentId, Model model) {
//        return "redirect:/usr/mypage/member/medical-appoint/medical-appointment-info";
//    }
//
//    @PostMapping("/usr/mypage/member/medical-appoint/medical-appointment-info/{medicalAppointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable Long medicalAppointmentId, LocalDateTime date) {
//
//        medicalAppointmentService.updateMedicalAppointment(medicalAppointmentId, date);
//
//        return "redirect:/usr/mypage/member/medical-appoint/medical-appointment-info";
//    }
}
