package com.codelion.animalcare.domain.appointment.controller;

import com.codelion.animalcare.domain.appointment.AppointmentSearch;
import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.DoctorService;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.PreferencesEvent;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/mypage/doctor/member-manage/appointments")
@RequiredArgsConstructor
public class AppointmentMyPageDoctorController {
    private final AppointmentService appointmentService;
    private final DiagnosisService diagnosisService;
    private final DoctorService doctorService;

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
                = appointmentService.findAllByDoctorEmail(email);
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
        appointmentService.updateAppointmentStatus(appointmentId, AppointmentStatus.REFUSE);

        return "redirect:/usr/mypage/doctor/member-manage/appointments";
    }

    // TODO 환자정보 확인

    /**
     * 환자 정보 확인
     */
    @GetMapping("{appointmentId}")
    public String loadMyPageDoctorAppointment(
            Model model,
            @PathVariable long appointmentId
    ){
        LoadMyPageDoctorAppointment.ResponseDto appointment
                = appointmentService.findById(appointmentId);

        FindOneDiagnosis diagnosis = diagnosisService.findByAppointmentId(appointment.getId());


        model.addAttribute("appointment", appointment);
        model.addAttribute("member", appointment.getMember());
        model.addAttribute("animal", appointment.getAnimal());
        model.addAttribute("hospital", appointment.getHospital());
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("doctor", appointment.getDoctor());
        return "myPage/doctor/member-manage-self";
    }

    // TODO 진단서 확인

}
