package com.codelion.animalcare.domain.doctor.controller;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import com.codelion.animalcare.domain.doctor.service.DoctorService;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentService;
import com.codelion.animalcare.domain.medical_record.entity.MedicalRecord;
import com.codelion.animalcare.domain.medical_record.service.MedicalRecordService;
import com.codelion.animalcare.domain.doctor.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.doctor.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageInfo;
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
    private final HospitalService hospitalService;
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

        // Entity => dto
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalForm = new LoadDoctorMyPageHospitalInfoManage.ResponseDto(hospital);

        model.addAttribute("hospitalForm", hospitalForm);

        return "myPage/doctor/hospital-info-manage";
    }

    // 병원 소개 수정 페이지
    @GetMapping("{doctorId}/hospital-info-manage/modify")
    public String loadDoctorMyPageHospitalInfoManageModify(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        Hospital hospital = doctor.getHospital();

        // Entity => dto
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalForm = new LoadDoctorMyPageHospitalInfoManage.ResponseDto(hospital);

        model.addAttribute("hospitalForm", hospitalForm);

        return "myPage/doctor/hospital-info-manage-modify";
    }

    // 병원 소개 수정 요청
    @PostMapping("{doctorId}/hospital-info-manage/modify")
    public String updateDoctorMyPageHospitalInfoManage(
            Model model,
            @PathVariable long doctorId,
            @RequestBody UpdateDoctorMyPageHospitalInfoManage.RequestDto body
    ){
        // doctor check
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // hospital check
        Hospital beforeHospital = doctor.getHospital();

        if(beforeHospital == null) throw new RuntimeException(doctorId + "don't have hospital information.");

        // DTO => Entity
        Hospital afterHospital = body.toEntity(beforeHospital);

        hospitalService.save(afterHospital);

        return "redirect:/usr/my-page/doctor/{doctorId}/hospital-info-manage";
    }

    // 환자 예약 관리
    @GetMapping("{doctorId}/patient-manage/medical-appointments")
    public String loadDoctorMyPagePatientManageMedicalAppointment(
            Model model,
            @PathVariable long doctorId,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        List<MedicalAppointment> medicalAppointments = medicalAppointmentService.findByDoctorId(doctorId);
        // TODO dto 사용
        model.addAttribute("medicalAppointments",medicalAppointments);

        return "myPage/doctor/patient-manage/medical-appointments";
    }

    //TODO 예약 환자 거절

    // 환자 진료서 관리
    @GetMapping("{doctorId}/patient-manage/medical-records")
    public String loadDoctorMyPagePatientManageMedicalRecord(
            Model model,
            @PathVariable long doctorId,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){

        List<MedicalRecord> medicalRecords = medicalRecordService.findByDoctorId(doctorId);
        // TODO dto 사용
        model.addAttribute("medicalRecords",medicalRecords);

        return "myPage/doctor/patient-manage/medical-records";
    }




    // 내 정보
    @GetMapping("{doctorId}/info")
    public String loadDoctorMyPageInfo(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // entity => dto
        LoadDoctorMyPageInfo.ResponseDto doctorForm = new LoadDoctorMyPageInfo.ResponseDto(doctor);

        model.addAttribute("doctor",doctorForm);

        return "myPage/doctor/info";
    }

    // 내 정보 수정 페이지
    @GetMapping("{doctorId}/info/modify")
    public String loadDoctorMyPageInfoModify(Model model, @PathVariable long doctorId){
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        // entity => dto
        LoadDoctorMyPageInfo.ResponseDto doctorForm = new LoadDoctorMyPageInfo.ResponseDto(doctor);

        model.addAttribute("doctor", doctorForm);

        return "myPage/doctor/info-modify";
    }


    // 내 정보 수정 요청(비밀번호 제외)
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

    // 비밀번호 수정.
    @PostMapping("{doctorId}/info/modify/password")
    public void updateDoctorMyPageInfoPassword(
            String loginPwd,
            @PathVariable long doctorId,
            Model model
    ){
        // doctor check
        Doctor doctor = doctorService.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "can't found."));

        doctor.updateLoginPwd(loginPwd);

        doctorService.save(doctor);
    }

}
