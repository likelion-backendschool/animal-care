package com.codelion.animalcare.webrtc.controller;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.hospital.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/mypage/doctor/member-manage/appointments")
@RequiredArgsConstructor
public class WebrtcAppointmentListController {

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

//    곧 지울거임
    @GetMapping("/new")
    public String createDiagnosisForm(Model model) {
        model.addAttribute("diagnosisForm", new FindOneDiagnosis());
        return "diagnosis/diagnosisForm";
    }

//  이제 이거 넣어야함. 넣었는데 왜 appointmentId가 안들어갈까
    @PostMapping("/new")
    public String writeDiagnosis(@Valid FindOneDiagnosis writtenDiagnosisForm, BindingResult result, Principal principal) {

        if (result.hasErrors()) {
            return "diagnosis/diagnosisForm";
        }
        FindOneDiagnosis newDiagnosisForm = new FindOneDiagnosis();

        newDiagnosisForm.setMemberName(writtenDiagnosisForm.getMemberName());
        newDiagnosisForm.setAddressCity(writtenDiagnosisForm.getAddressCity());
        newDiagnosisForm.setAddressStreet(writtenDiagnosisForm.getAddressStreet());

        newDiagnosisForm.setBreedingPlace(writtenDiagnosisForm.getBreedingPlace());
        newDiagnosisForm.setAnimalType(writtenDiagnosisForm.getAnimalType());
        newDiagnosisForm.setAnimalBreed(writtenDiagnosisForm.getAnimalBreed());
        newDiagnosisForm.setAnimalName(writtenDiagnosisForm.getAnimalName());
        newDiagnosisForm.setAnimalGenderId(writtenDiagnosisForm.getAnimalGenderId());
        newDiagnosisForm.setAnimalAge(writtenDiagnosisForm.getAnimalAge());
        newDiagnosisForm.setAnimalCoatColor(writtenDiagnosisForm.getAnimalCoatColor());
        newDiagnosisForm.setAnimalSpecial(writtenDiagnosisForm.getAnimalSpecial());
        newDiagnosisForm.setDiseaseName(writtenDiagnosisForm.getDiseaseName());

//        newDiagnosisForm.setDiseaseDate(writtenDiagnosisForm.getDiseaseDate());
//        newDiagnosisForm.setDiagnosisDate(writtenDiagnosisForm.getDiagnosisDate());
//      날짜는 임시로 넣어둠
        newDiagnosisForm.setDiseaseDate(LocalDateTime.now());
        newDiagnosisForm.setDiagnosisDate(LocalDateTime.now());

        newDiagnosisForm.setOpinion(writtenDiagnosisForm.getOpinion());
        newDiagnosisForm.setOtherMatter(writtenDiagnosisForm.getOtherMatter());

        newDiagnosisForm.setHospitalName(writtenDiagnosisForm.getHospitalName());
        newDiagnosisForm.setHospitalStreet(writtenDiagnosisForm.getHospitalStreet());
        newDiagnosisForm.setDoctorLicense(writtenDiagnosisForm.getDoctorLicense());
        newDiagnosisForm.setDoctorName(writtenDiagnosisForm.getDoctorName());



//        String email = principal.getName();
//        List<LoadMyPageDoctorAppointment.ResponseDto> appointments = appointmentService.findAllByDoctorEmail(email);
//
////        하나를 가져와야하는데
////        model.addAttribute("appointments", appointments);
////        return "myPage/doctor/member-manage";
//        appointments.get(0).getId();
////
//        //appointmentId 추가하기
//        newDiagnosisForm.setAppointment(appointments.get(0));

        diagnosisService.diagnosis(newDiagnosisForm);

        return "redirect:/";

    }

    //위 두개 메서드를 아래에 추가 구현해야함
    //예약명단에서 바로 진단서 작성하게 구현하기
    @GetMapping("/all/{appointmentId}")
    public String createDiagnosisNewForm(@PathVariable("appointmentId") Long appointmentId,
                                        Model model,
                                        Principal principal) {

            model.addAttribute("diagnosisForm", new FindOneDiagnosis());
            return "diagnosis/diagnosisForm";
    }

    @PostMapping("/all/{appointmentId}")
    public String writeNewDiagnosis(@PathVariable("appointmentId") Long appointmentId, @Valid FindOneDiagnosis writtenDiagnosisForm, BindingResult result, Principal principal) {

        if (result.hasErrors()) {
            return "diagnosis/diagnosisForm";
        }
        FindOneDiagnosis newDiagnosisForm = new FindOneDiagnosis();

        newDiagnosisForm.setMemberName(writtenDiagnosisForm.getMemberName());
        newDiagnosisForm.setAddressCity(writtenDiagnosisForm.getAddressCity());
        newDiagnosisForm.setAddressStreet(writtenDiagnosisForm.getAddressStreet());

        newDiagnosisForm.setBreedingPlace(writtenDiagnosisForm.getBreedingPlace());
        newDiagnosisForm.setAnimalType(writtenDiagnosisForm.getAnimalType());
        newDiagnosisForm.setAnimalBreed(writtenDiagnosisForm.getAnimalBreed());
        newDiagnosisForm.setAnimalName(writtenDiagnosisForm.getAnimalName());
        newDiagnosisForm.setAnimalGenderId(writtenDiagnosisForm.getAnimalGenderId());
        newDiagnosisForm.setAnimalAge(writtenDiagnosisForm.getAnimalAge());
        newDiagnosisForm.setAnimalCoatColor(writtenDiagnosisForm.getAnimalCoatColor());
        newDiagnosisForm.setAnimalSpecial(writtenDiagnosisForm.getAnimalSpecial());
        newDiagnosisForm.setDiseaseName(writtenDiagnosisForm.getDiseaseName());

//        newDiagnosisForm.setDiseaseDate(writtenDiagnosisForm.getDiseaseDate());
//        newDiagnosisForm.setDiagnosisDate(writtenDiagnosisForm.getDiagnosisDate());
//      날짜는 임시로 넣어둠
        newDiagnosisForm.setDiseaseDate(LocalDateTime.now());
        newDiagnosisForm.setDiagnosisDate(LocalDateTime.now());

        newDiagnosisForm.setOpinion(writtenDiagnosisForm.getOpinion());
        newDiagnosisForm.setOtherMatter(writtenDiagnosisForm.getOtherMatter());

        newDiagnosisForm.setHospitalName(writtenDiagnosisForm.getHospitalName());
        newDiagnosisForm.setHospitalStreet(writtenDiagnosisForm.getHospitalStreet());
        newDiagnosisForm.setDoctorLicense(writtenDiagnosisForm.getDoctorLicense());
        newDiagnosisForm.setDoctorName(writtenDiagnosisForm.getDoctorName());



//        String email = principal.getName();
//        List<LoadMyPageDoctorAppointment.ResponseDto> appointments = appointmentService.findAllByDoctorEmail(email);
//
////        하나를 가져와야하는데
////        model.addAttribute("appointments", appointments);
////        return "myPage/doctor/member-manage";
//        appointments.get(0).getId();
////
//        //appointmentId 추가하기
//        newDiagnosisForm.setAppointment(appointments.get(0));

//        appointmentId
//        Optional<AppointmentDto> appointmentDto = appointmentService.findById(appointmentId);
//        AppointmentDto appointmentDto1 = appointmentDto.get();

        Appointment appointment = appointmentService.findAppointmentById(appointmentId);

        newDiagnosisForm.setAppointment(appointment);

//        FindOneDiagnosis diagnosis = diagnosisService.findByAppointmentId(appointment.getId());

        diagnosisService.diagnosis(newDiagnosisForm);

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
