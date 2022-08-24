package com.codelion.animalcare.domain.medical_appointment.controller;

import com.codelion.animalcare.domain.medical_appointment.dto.LoadMyPageDoctorMedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/usr/mypage/doctor/{doctorId}/member-manage/medical-appointments")
@RequiredArgsConstructor
public class MedicalAppointmentMyPageDoctorController {
    private final MedicalAppointmentService medicalAppointmentService;

    // 환자 예약 관리
    @GetMapping()
    public String loadMyPageDoctorMedicalAppointment(
            Model model,
            @PathVariable long doctorId,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        List<LoadMyPageDoctorMedicalAppointment.ResponseDto> medicalAppointments
                = medicalAppointmentService.findAllByDoctorId(doctorId);
        // TODO dto 사용
        model.addAttribute("medicalAppointments", medicalAppointments);

        return "myPage/doctor/member-manage";
    }

    //TODO 예약 환자 거절

}
