package com.codelion.animalcare.webrtc.controller;

import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.appointment.service.AppointmentCommandService;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/usr/doctor/medicalOffice")
@RequiredArgsConstructor
public class WebrtcDoctorMedicalOfficeController {

    private final AppointmentQueryService appointmentQueryService;
    private final AppointmentCommandService appointmentCommandService;
    private final DiagnosisService diagnosisService;

    /**
     * 비대면 진료
     * Doctor가 예약내역 확인
     */
    @GetMapping()
    public String loadByDoctorAppointments(Model model, Principal principal) {

        String email = principal.getName();
        List<AppointmentDto> appointmentDto = appointmentQueryService.findAllAppointment(email);

        model.addAttribute("appointmentDto", appointmentDto);

        return "webrtc/diagnosis/appointment-info-in-medicaloffice";
    }


    /**
     *
     * 예약명단에서 바로 진단서 작성
     */
    @GetMapping("/{appointmentId}")
    public String createDiagnosisNewForm(@PathVariable("appointmentId") long appointmentId, Model model) {

        LoadMyPageDoctorAppointment.ResponseDto appointmentDto = appointmentQueryService.findById(appointmentId);

        FindOneDiagnosis diagnosis = diagnosisService.findByAppointmentId(appointmentDto.getId());

        model.addAttribute("appointment", appointmentDto);
        model.addAttribute("member", appointmentDto.getMember());
        model.addAttribute("animal", appointmentDto.getAnimal());
        model.addAttribute("hospital", appointmentDto.getHospital());
        model.addAttribute("doctor", appointmentDto.getDoctor());
        model.addAttribute("diagnosis", diagnosis);

        model.addAttribute("diagnosisForm", new FindOneDiagnosis());


        return "webrtc/diagnosis/create-diagnosis";
    }

    @PostMapping("/{appointmentId}")
    public String writeNewDiagnosis(@PathVariable("appointmentId") long appointmentId,
                                    @Valid FindOneDiagnosis writtenDiagnosisForm) {

        LoadMyPageDoctorAppointment.ResponseDto appointmentDto = appointmentQueryService.findById(appointmentId);
        diagnosisService.diagnosis(appointmentDto, writtenDiagnosisForm, AppointmentStatus.COMPLETE);

        return "redirect:/usr/doctor/medicalOffice";
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
