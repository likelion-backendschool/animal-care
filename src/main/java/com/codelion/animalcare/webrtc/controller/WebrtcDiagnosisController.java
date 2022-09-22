package com.codelion.animalcare.webrtc.controller;


import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.hospital.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.DoctorService;
import com.codelion.animalcare.domain.user.service.MemberService;
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
@RequiredArgsConstructor
@RequestMapping("/usr/mypage/doctor/diagnosis/info")
public class WebrtcDiagnosisController {

    private final DiagnosisService diagnosisService;
    private final AppointmentService appointmentService;
    private final MemberService memberService;
    private final DoctorService doctorService;

    @GetMapping("/new")
    public String createDiagnosisForm(Model model) {
        model.addAttribute("diagnosisForm", new FindOneDiagnosis());
        return "diagnosis/diagnosisForm";
    }


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

//    @GetMapping("/{appointmentId}")
//    public String updateAppointmentForm(@PathVariable("appointmentId") Long appointmentId,
//                                        Model model,
//                                        Principal principal) {
//
//        Optional<AppointmentDto> appointmentDto = appointmentService.findById(appointmentId);
//
//        Optional<MemberDto> memberDto = memberService.findByEmail(principal.getName());
//        List<AnimalDto> animalDtos = animalService.findByMember(memberDto.get());
//
//        List<LoadDoctorMyPageHospitalInfoManage.ResponseDto> hospitalDtos = hospitalService.findHospitals();
//        List<LoadDoctorMyPageInfo.ResponseDto> doctorDtos = doctorService.findDoctors();
//
//        model.addAttribute("appointmentDto", appointmentDto.get());
//        model.addAttribute("memberDto", memberDto.get());
//        model.addAttribute("animalDtos", animalDtos);
//        model.addAttribute("hospitalDtos", hospitalDtos);
//        model.addAttribute("doctorDtos", doctorDtos);
//
//
//        return "appointments/appointmentModifyForm";
//    }




    // TODO 진단서, 예약서 매핑하기
    @GetMapping("/new2")
    public String createDiagnosisForm2(Model model) {
        model.addAttribute("diagnosisForm", new FindOneDiagnosis());
        return "diagnosis/diagnosisForm";
    }

//    @PostMapping("/new2/diagnosis-tmp")
//    public String writeDiagnosis2(@Valid FindOneDiagnosis writtenForm
//            , BindingResult result
//            , Model model
//            , Principal principal
////            , @PathVariable long appointmentId
//    ) {

//        if (result.hasErrors()) {
//            return "diagnosis/diagnosisForm";
//        }
//
//        String email = principal.getName();
//        Optional<MemberDto> memberOptionalDto = memberService.findByEmail(email);
//        MemberDto memberDto = memberOptionalDto.get();
//
//
//        LoadMyPageDoctorAppointment.ResponseDto appointment
//                = appointmentService.findById(appointmentId);
//
//        FindOneDiagnosis diagnosis = diagnosisService.findByAppointmentId(appointment.getId());
//
//        model.addAttribute("appointment", appointment);
//        model.addAttribute("member", appointment.getMember());
//        model.addAttribute("animal", appointment.getAnimal());
//        model.addAttribute("hospital", appointment.getHospital());
//        model.addAttribute("diagnosis", diagnosis);
//        model.addAttribute("doctor", appointment.getDoctor());
//        return "myPage/doctor/member-manage-diagnosis";
//
//
//        FindOneDiagnosis newForm = new FindOneDiagnosis();
//
//        newForm.setMemberName(writtenForm.getMemberName());
//        newForm.setAddressCity(writtenForm.getAddressCity());
//        newForm.setAddressStreet(writtenForm.getAddressStreet());
//
//        newForm.setBreedingPlace(writtenForm.getBreedingPlace());
//        newForm.setAnimalType(writtenForm.getAnimalType());
//        newForm.setAnimalBreed(writtenForm.getAnimalBreed());
//        newForm.setAnimalName(writtenForm.getAnimalName());
//        newForm.setAnimalGenderId(writtenForm.getAnimalGenderId());
//        newForm.setAnimalAge(writtenForm.getAnimalAge());
//        newForm.setAnimalCoatColor(writtenForm.getAnimalCoatColor());
//        newForm.setAnimalSpecial(writtenForm.getAnimalSpecial());
//        newForm.setDiseaseName(writtenForm.getDiseaseName());
//
////        newForm.setDiseaseDate(form.getDiseaseDate());
////        newForm.setDiagnosisDate(form.getDiagnosisDate());
////      날짜는 임시로 넣어둠
//        newForm.setDiseaseDate(LocalDateTime.now());
//        newForm.setDiagnosisDate(LocalDateTime.now());
//
//        newForm.setOpinion(writtenForm.getOpinion());
//        newForm.setOtherMatter(writtenForm.getOtherMatter());
//
//        newForm.setHospitalName(writtenForm.getHospitalName());
//        newForm.setHospitalStreet(writtenForm.getHospitalStreet());
//        newForm.setDoctorLicense(writtenForm.getDoctorLicense());
//        newForm.setDoctorName(writtenForm.getDoctorName());
//
//
//        diagnosisService.diagnosis(newForm);
//
//        return "redirect:/usr/mypage/doctor/member-manage/appointments";

//    }

//    @GetMapping("/new/{appointmentId}/diagnosis-tmp")
//    public String loadMyPageDoctorDiagnosis(
//            Model model,
//            @PathVariable long appointmentId
//    ){
//        LoadMyPageDoctorAppointment.ResponseDto appointment
//                = appointmentService.findById(appointmentId);
//
//        FindOneDiagnosis diagnosis = diagnosisService.findByAppointmentId(appointment.getId());
//
//
//        model.addAttribute("appointment", appointment);
//        model.addAttribute("member", appointment.getMember());
//        model.addAttribute("animal", appointment.getAnimal());
//        model.addAttribute("hospital", appointment.getHospital());
//        model.addAttribute("diagnosis", diagnosis);
//        model.addAttribute("doctor", appointment.getDoctor());
//        return "myPage/doctor/member-manage-diagnosis";
//    }

}
