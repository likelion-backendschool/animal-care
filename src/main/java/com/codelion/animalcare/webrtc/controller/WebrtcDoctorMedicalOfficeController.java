package com.codelion.animalcare.webrtc.controller;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.user.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/usr/mypage/doctor/member-manage/appointments")
@RequiredArgsConstructor
public class WebrtcDoctorMedicalOfficeController {

    private final DoctorService doctorService;
    private final AppointmentQueryService appointmentQueryService;
    private final AppointmentService appointmentService;
    private final DiagnosisService diagnosisService;


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


    /**
     *
     * 예약명단에서 바로 진단서 작성
     */
    @GetMapping("/all/{appointmentId}")
    public String createDiagnosisNewForm(@PathVariable("appointmentId") long appointmentId, Model model) {

        LoadMyPageDoctorAppointment.ResponseDto appointmentDto = appointmentService.findById(appointmentId);

        FindOneDiagnosis diagnosis = diagnosisService.findByAppointmentId(appointmentDto.getId());

        model.addAttribute("appointment", appointmentDto);
        model.addAttribute("member", appointmentDto.getMember());
        model.addAttribute("animal", appointmentDto.getAnimal());
        model.addAttribute("hospital", appointmentDto.getHospital());
        model.addAttribute("doctor", appointmentDto.getDoctor());
        model.addAttribute("diagnosis", diagnosis);

        model.addAttribute("oneDiagnosisForm", new FindOneDiagnosis());


        return "diagnosis/diagnosisForm";
    }

    @PostMapping("/all/{appointmentId}")
    public String writeNewDiagnosis(@PathVariable("appointmentId") long appointmentId,
                                    @Valid FindOneDiagnosis writtenDiagnosisForm) {

        LoadMyPageDoctorAppointment.ResponseDto appointmentDto = appointmentService.findById(appointmentId);
        diagnosisService.diagnosis(appointmentDto, writtenDiagnosisForm);

        // 왜 안먹힐까
        // TODO redirect 가능하게 하기. 진단서 작성 완료시 예약내역으로 이동해야함
        return "redirect:/usr/mypage/doctor/member-manage/appointments/all";
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
