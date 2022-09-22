package com.codelion.animalcare.domain.hospital.controller;

import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.hospital.dto.CreateHospital;
import com.codelion.animalcare.domain.hospital.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.dto.UpdateDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import com.codelion.animalcare.domain.user.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("usr/doctor/mypage/hospital")
@RequiredArgsConstructor
public class HospitalMyPageDoctorController {
    private final HospitalService hospitalService;
    private final DoctorService doctorService;

    // 병원 소개
    @GetMapping()
    public String loadDoctorMyPageHospitalInfoManage(
            Model model,
            Principal principal
    ){
        String doctorEmail = principal.getName();
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalDto = null;

        try{
            hospitalDto = hospitalService.findByDoctorEmail(doctorEmail);
        } catch(RuntimeException e){ // 병원이 등록되지 않았다면
            return "myPage/doctor/hospital-create-or-select";
        }

        model.addAttribute("hospital", hospitalDto);
        model.addAttribute("OpeningHours", hospitalDto.makeOpeningHoursToObject());

        System.out.println(hospitalDto.getDoctorList());
        return "myPage/doctor/hospital-info-manage";
    }


    /**
     * 병원 생성
     */
    @GetMapping("create")
    public String loadDoctorMyPageHospitalCreate(
            Model model,
            CreateHospital.RequestDto requestDto
    ){

        model.addAttribute("requestDto", requestDto);
        // TODO modify를 form으로 이름을 바꾼다.
        return "myPage/doctor/hospital-info-manage-create";
    }

    @PostMapping("create")
    // 병원 생성
    public String doDoctorMyPageHospitalCreate(
            Model model,
            Principal principal,
            @Valid CreateHospital.RequestDto requestDto,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "myPage/doctor/hospital-info-manage-create";
        }

        String doctorEmail = principal.getName();

        hospitalService.create(requestDto, doctorEmail);

        // TODO modify를 form으로 이름을 바꾼다.
        return "redirect:/usr/doctor/mypage/hospital";
    }

    /**
     * 병원 선택
     */
    @GetMapping("select")
    public String loadDoctorMyPageHospitalList(
        Model model,
        Principal principal
    ){
        // 자신의 경도 위도
        String doctorEmail = principal.getName();
        LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findByEmail(doctorEmail);

        model.addAttribute("doctorAddress", doctorDto.getAddress());
        // TODO modify를 form으로 이름을 바꾼다.
        return "myPage/doctor/hospital-info-manage-select";
    }

    @PostMapping("select")
    public String loadDoctorMyPageHospitalList(
            Principal principal,
            Long hospitalId
    ){
        // 자신의 경도 위도
        String doctorEmail = principal.getName();
        LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findByEmail(doctorEmail);

        doctorService.addHospital(doctorDto.getId(), hospitalId);
        return "redirect:/usr/doctor/mypage/hospital";
    }

    // 병원 소개 수정 페이지
    @GetMapping("modify")
    public String loadDoctorMyPageHospitalInfoManageModify(
            Model model,
            Principal principal
    ){
        String doctorEmail = principal.getName();
        LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalDto = hospitalService.findByDoctorEmail(doctorEmail);

        model.addAttribute("requestDto", hospitalDto);

        return "doctor/mypage/hospital-info-manage-modify";
    }

    // 병원 소개 수정 요청
    @PostMapping("modify")
    public String updateDoctorMyPageHospitalInfoManage(
            Model model,
            @Valid UpdateDoctorMyPageHospitalInfoManage.RequestDto requestDto,
            BindingResult bindingResult
        ){
            if(bindingResult.hasErrors()){
                return "myPage/doctor/hospital-info-manage-modify";
            }

            hospitalService.update(requestDto);

        return "redirect:/usr/doctor/mypage/hospital";
    }
}
