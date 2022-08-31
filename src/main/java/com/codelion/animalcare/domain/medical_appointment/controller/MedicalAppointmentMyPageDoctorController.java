package com.codelion.animalcare.domain.medical_appointment.controller;

import com.codelion.animalcare.domain.medical_appointment.MedicalAppointmentStatus;
import com.codelion.animalcare.domain.medical_appointment.dto.LoadMyPageDoctorMedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/usr/mypage/doctor/member-manage/medical-appointments")
@RequiredArgsConstructor
public class MedicalAppointmentMyPageDoctorController {
    private final MedicalAppointmentService medicalAppointmentService;


    // TODO page 설정
    /**
     * 환자 예약 관리
     */
    @GetMapping()
    public String loadMyPageDoctorMedicalAppointments(
            Model model,
            Principal principal,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        String email = principal.getName();
        List<LoadMyPageDoctorMedicalAppointment.ResponseDto> medicalAppointments
                = medicalAppointmentService.findAllByDoctorEmail(email);
        model.addAttribute("medicalAppointments", medicalAppointments);
        return "myPage/doctor/member-manage";
    }

    /**
     * 예약 환자 거절 기능
     */
    @PostMapping("{medicalAppointmentId}/refuse")
    public String refuseMedicalAppointment(
            @PathVariable long medicalAppointmentId
    ){
        medicalAppointmentService.updateMedicalAppointmentStatus(medicalAppointmentId, MedicalAppointmentStatus.REFUSE);

        return "redirect:/usr/mypage/doctor/member-manage/medical-appointments";
    }

    // TODO 환자정보 확인

    /**
     * 환자 정보 확인
     */
    @GetMapping("{medicalAppointmentId}")
    public String loadMyPageDoctorMedicalAppointment(
            Model model,
            @PathVariable long medicalAppointmentId
    ){
        LoadMyPageDoctorMedicalAppointment.ResponseDto medicalAppointment
                = medicalAppointmentService.findById(medicalAppointmentId);

        model.addAttribute("medicalAppointment", medicalAppointment);
        model.addAttribute("member", medicalAppointment.getMember());
        model.addAttribute("animal", medicalAppointment.getAnimal());
        model.addAttribute("hospital", medicalAppointment.getHospital());
        model.addAttribute("diagnosis", medicalAppointment.getDiagnosis());
        model.addAttribute("doctor", medicalAppointment.getDoctor());
        return "myPage/doctor/member-manage-self";
    }

    // TODO 진단서 확인

}
