package com.codelion.animalcare.web.mypage.controller;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.service.DoctorService;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentService;
import com.codelion.animalcare.domain.medical_record.entity.MedicalRecord;
import com.codelion.animalcare.domain.medical_record.service.MedicalRecordService;
import com.codelion.animalcare.web.mypage.dto.UpdateDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.web.mypage.dto.UpdateDoctorMyPageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usr/my-page/doctor")
@RequiredArgsConstructor
public class DoctorMyPageController {
    private final DoctorService doctorService;
    private final MedicalAppointmentService medicalAppointmentService;
    private final MedicalRecordService medicalRecordService;


    @GetMapping()
    public String loadDoctorMyPage(){
        return "myPage/doctor/index";
    }

    // 병원 소개
    @GetMapping("{doctorId}/hospital-info-manage")
    public String loadDoctorMyPageHospitalInfoManage(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        Hospital hospital = doctor.getHospital();

        model.addAttribute("hospital", hospital);
        return "myPage/doctor/hospital-info-manage";
    }

    // 병원 소개 수정 페이지
    @GetMapping("{doctorId}/hospital-info-manage/modify")
    public String loadDoctorMyPageHospitalInfoManageModify(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        Hospital hospital = doctor.getHospital();

        model.addAttribute("hospital", hospital);
        return "myPage/doctor/hospital-info-manage-modify";
    }

    // 병원 소개 수정 요청
    @PostMapping("{doctorId}/hospital-info-manage/doModify")
    public String updateDoctorMyPageHospitalInfoManage(
            Model model,
            @PathVariable long doctorId,
            @RequestBody UpdateDoctorMyPageHospitalInfoManage.RequestDto body
    ){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        Hospital hospital = doctor.getHospital();

        model.addAttribute("hospital", hospital);
        return "myPage/doctor/hospital-info-manage-modify";
    }

    // 환자 관리
    @GetMapping("{doctorId}/patient-manage")
    public String loadDoctorMyPagePatientManage(Model model, @PathVariable long doctorId){
        List<MedicalAppointment> medicalAppointments = medicalAppointmentService.findByDoctorId(doctorId);
        List<MedicalRecord> medicalRecords = medicalRecordService.findByDoctorId(doctorId);

        model.addAttribute("medicalAppointments",medicalAppointments);
        model.addAttribute("medicalRecords",medicalRecords);

        return "myPage/doctor/patient-manage";
    }

    //TODO 예약 환자 거절


    // 내 정보
    @GetMapping("{doctorId}/info")
    public String loadDoctorMyPageInfo(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        model.addAttribute("doctor",doctor);

        return "myPage/doctor/info";
    }

    // 내 정보 수정 페이지
    @GetMapping("{doctorId}/info/modify")
    public String loadDoctorMyPageInfoModify(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        model.addAttribute("doctor", doctor);

        return "myPage/doctor/info-modify";
    }

    // 내 정보 수정 요청
    @PostMapping("{doctorId}/info/modify")
    public String updateDoctorMyPageInfo(
            UpdateDoctorMyPageInfo.RequestDto body,
            @PathVariable long doctorId,
            Model model
            ){
        // 존재 하는지 체크
        Doctor beforeDoctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // dto => entity
        Doctor afterDoctor = body.toEntity(beforeDoctor);
        doctorService.save(afterDoctor);

        return "redirect:/usr/my-page/doctor/{doctorId}/info";
    }
}
