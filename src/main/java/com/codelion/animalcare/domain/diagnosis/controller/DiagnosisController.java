package com.codelion.animalcare.domain.diagnosis.controller;

import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.diagnosis.DiagnosisSearch;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/mypage/doctor/diagnosis/info")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final AppointmentService appointmentService;


    // 닥터페이지 진단서
    @GetMapping("/new")
    public String createDiagnosisForm(Model model) {
        model.addAttribute("diagnosisForm", new FindOneDiagnosis());
        return "diagnosis/diagnosisForm";
    }


    @PostMapping("/new")
    public String writeDiagnosis(@Valid FindOneDiagnosis writtenDiagnosisForm, BindingResult result) {

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


        diagnosisService.diagnosis(newDiagnosisForm);

        return "redirect:/usr/mypage/doctor/member-manage/appointments";

    }


// TODO 진단서, 예약서 매핑하기
//    @GetMapping("/new2")
//    public String createDiagnosisForm2(Model model) {
//        model.addAttribute("diagnosisForm", new FindOneDiagnosis());
//        return "diagnosis/diagnosisForm";
//    }
//
//    @PostMapping("/new2/{appointmentId}/diagnosis-tmp")
//    public String writeDiagnosis2(@Valid FindOneDiagnosis writtenForm
//            , BindingResult result
//            , Model model
//            , @PathVariable long appointmentId) {
//
//        if (result.hasErrors()) {
//            return "diagnosis/diagnosisForm";
//        }
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
//
//    }
//
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
